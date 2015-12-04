package com.coco.spring.boot.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.spring.boot.web.ICallBack;
import com.coco.spring.boot.web.https.Http12306Client;

@Controller
public class OtnController {

	@Resource
	private Http12306Client httpclient;

	@RequestMapping("/otn/**")
	public void otn(HttpServletRequest request, final HttpServletResponse response) throws Exception {

		Enumeration<String> enumeration = request.getParameterNames();
		Map<String, String> maps = new HashMap<>();
		while (enumeration.hasMoreElements()) {
			String next = enumeration.nextElement();
			maps.put(next, request.getParameter(next));
		}

		String method = request.getMethod();
		if ("GET".equalsIgnoreCase(method)) {
			final StringBuilder uri = new StringBuilder(request.getRequestURI());
			if (StringUtils.isNotBlank(request.getQueryString())) {
				uri.append("?").append(request.getQueryString());
			}
			httpclient.get("https://kyfw.12306.cn/" + uri, new ICallBack() {
				@Override
				public void callBack(HttpEntity obj) {
					try {
						System.out.println(uri.toString() + "\t" + obj.getContentType());
						if (obj.isStreaming()) {
							obj.writeTo(response.getOutputStream());
						} else {
							System.out.println(EntityUtils.toString(obj));
						}
					} catch (ParseException | IOException e) {
					}
				}
			});
		} else {
			httpclient.get("https://kyfw.12306.cn/" + request.getRequestURI(), new ICallBack() {

				@Override
				public void callBack(HttpEntity obj) {
					try {
						if (obj.isStreaming()) {
							IOUtils.copy(obj.getContent(), response.getOutputStream());
						}
					} catch (ParseException | IOException e) {
					}
				}
			}, maps);
		}
	}

	
	
	
	@RequestMapping("/otn/resources/**")
	public void otnResources(HttpServletRequest request, final HttpServletResponse response) throws Exception {

		Enumeration<String> enumeration = request.getParameterNames();
		Map<String, String> maps = new HashMap<>();
		while (enumeration.hasMoreElements()) {
			String next = enumeration.nextElement();
			maps.put(next, request.getParameter(next));
		}

		String method = request.getMethod();
		if ("GET".equalsIgnoreCase(method)) {
			final StringBuilder uri = new StringBuilder(request.getRequestURI());
			if (StringUtils.isNotBlank(request.getQueryString())) {
				uri.append("?").append(request.getQueryString());
			}
			httpclient.get("https://kyfw.12306.cn/" + uri, new ICallBack() {
				@Override
				public void callBack(HttpEntity obj) {
					try {
						System.out.println(uri.toString() + "\t" + obj.getContentType());
						if (obj.isStreaming()) {
							obj.writeTo(response.getOutputStream());
						} else {
							System.out.println(EntityUtils.toString(obj));
						}
					} catch (ParseException | IOException e) {
					}
				}
			});
		} else {
			httpclient.get("https://kyfw.12306.cn/" + request.getRequestURI(), new ICallBack() {

				@Override
				public void callBack(HttpEntity obj) {
					try {
						if (obj.isStreaming()) {
							IOUtils.copy(obj.getContent(), response.getOutputStream());
						}
					} catch (ParseException | IOException e) {
					}
				}
			}, maps);
		}
	}

	
	
	@RequestMapping("/otn/leftTicket/**")
	@ResponseBody
	public String leftTicket(HttpServletRequest request, final HttpServletResponse response) throws Exception {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			httpclient.setCookie(cookie.getName(), cookie.getValue());
		}

		final StringBuilder retVal = new StringBuilder();
		String method = request.getMethod();
		if ("GET".equalsIgnoreCase(method)) {
			final StringBuilder uri = new StringBuilder(request.getRequestURI());
			if (StringUtils.isNotBlank(request.getQueryString())) {
				uri.append("?").append(request.getQueryString());
			}
			httpclient.get("https://kyfw.12306.cn/" + uri, new ICallBack() {
				@Override
				public void callBack(HttpEntity obj) {
					try {
						retVal.append(EntityUtils.toString(obj));
					} catch (ParseException | IOException e) {
					}
				}
			});
		} else {
			Enumeration<String> enumeration = request.getParameterNames();
			Map<String, String> maps = new HashMap<>();
			while (enumeration.hasMoreElements()) {
				String next = enumeration.nextElement();
				maps.put(next, request.getParameter(next));
			}
			httpclient.get("https://kyfw.12306.cn/" + request.getRequestURI(), new ICallBack() {

				@Override
				public void callBack(HttpEntity obj) {
					try {
						retVal.append(EntityUtils.toString(obj));
					} catch (ParseException | IOException e) {
					}
				}
			}, maps);
		}
	
		return retVal.toString();
	}

}
