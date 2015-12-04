package com.coco.spring.boot.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.coco.spring.boot.web.ICallBack;
import com.coco.spring.boot.web.PositionCaptcha;
import com.coco.spring.boot.web.URLS_12306;
import com.coco.spring.boot.web.https.Http12306Client;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/demo")
public class DemoController {

	@Resource
	private Http12306Client httpclient;

	@RequestMapping(value = "/example")
	public ModelAndView example(HttpServletRequest request) {
		return new ModelAndView("/page/index.html");
	}

	@RequestMapping(value = "/captcheCode")
	public void captcheCode(HttpServletRequest request, final HttpServletResponse response) {

		Map<String, String> paras = new HashMap<>();
		paras.put("module", "login");
		paras.put("rand", "sjrand");
		try {
			httpclient.get(URLS_12306.LOGIN_CAPTCHA, new ICallBack() {
				@Override
				public void callBack(HttpEntity obj) {

					if (obj.isStreaming()) {
						try {
							IOUtils.copy(obj.getContent(), response.getOutputStream());
						} catch (IOException e) {
						}
					}
				}
			}, paras);
		} catch (Exception e) {
		}

	}

	@RequestMapping(value = "/login")
	@ResponseBody
	public StringBuilder login(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord,
			@RequestParam("captchecodes") String captchecodes) {
		final StringBuilder retVal = new StringBuilder();
		Map<String, String> maps = new HashMap<>();
		
		maps.put("rand", "sjrand");
		maps.put("randCode", PositionCaptcha.getCaptchaPosition(captchecodes));
		try {
			httpclient.post(URLS_12306.CHECK_CAPTCHA, new ICallBack() {
				@Override
				public void callBack(HttpEntity obj) {
					try {
						String str = EntityUtils.toString(obj);
						System.out.println(str);
						JSONObject jsonObject = JSONObject.fromObject(str);
						Object retStatus = ((JSONObject) jsonObject.get("data")).get("result");
						if (!retStatus.equals("1")) {
							throw new RuntimeException("验证码错误");
						} 
					} catch (ParseException | IOException e) {
					}
				}
			}, maps);
			maps.clear();
			maps.put("loginUserDTO.user_name", userName);
			maps.put("userDTO.password", passWord);
			maps.put("randCode", PositionCaptcha.getCaptchaPosition(captchecodes));
			httpclient.post(URLS_12306.LOGIN_URL, new ICallBack() {
				@Override
				public void callBack(HttpEntity obj) {
					String str = null;
					try {
						str = EntityUtils.toString(obj);
					} catch (Exception e) {
					}
					System.out.println(str);
					JSONObject jsonObject = JSONObject.fromObject(str);
					JSONObject retStatus = ((JSONObject) jsonObject.get("data"));
					if ("Y".equals(retStatus.get("loginCheck"))) {
						Map<String, String> hashMap = new HashMap<>();
						hashMap.put("_json_att", "");
						try {
//							httpclient.post(URLS_12306.LOGIN_USER, hashMap);
							httpclient.get(URLS_12306.LOGIN_USER, new ICallBack() {
								@Override
								public void callBack(HttpEntity obj) {
									try {
										retVal.append(EntityUtils.toString(obj));
									} catch (Exception e) {
									}
								}
							});
						} catch (Exception e) {
							
						}
					}else{
						retVal.append(str);
					}
				}
			}, maps);
		} catch (Exception e1) {
		}

		return retVal;
	}
}
