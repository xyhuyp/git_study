package com.coco.spring.boot.web.https;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;

import com.coco.spring.boot.web.ICallBack;
import com.coco.spring.boot.web.util.EscapeUnescape;

public class Http12306Client {

	private CloseableHttpClient httpclient;

	private BasicCookieStore cookieStore;

	public Http12306Client() {
		SSLContext sslcontext = null;
		try {
			sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null, new TrustManager[] { new DefaultX509TrustManager() }, null);
		} catch (Exception e) {
		}
		cookieStore = new BasicCookieStore();
		httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).setSSLContext(sslcontext)
				.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
				.setUserAgent("Mozilla/5.0 (X11; Linux x86_64; rv:42.0) Gecko/20100101 Firefox/42.0").build();

	}

	public void get(String url, ICallBack callBack, Map<String, String> paras) throws Exception {
		RequestBuilder requestBuilder = RequestBuilder.get().setUri(url);
		if (paras != null) {
			for (Entry<String, String> entry : paras.entrySet()) {
				requestBuilder.addParameter(entry.getKey(), entry.getValue());
			}
		}
		CloseableHttpResponse initResponse = httpclient.execute(requestBuilder.build());
		try {
			HttpEntity entity = initResponse.getEntity();
			callBack.callBack(entity);
		} finally {
			initResponse.close();
		}
	}

	public void get(String url, Map<String, String> paras) throws Exception {
		get(url, new ICallBack() {
			@Override
			public void callBack(HttpEntity obj) {

			}
		}, paras);
	}

	public void get(String url) throws Exception {
		get(url, new ICallBack() {
			@Override
			public void callBack(HttpEntity obj) {
				try {
				} catch (Exception e) {
				}
			}
		}, null);
	}

	public void get(String url, ICallBack callBack) throws Exception {
		get(url, callBack, null);
	}

	public void post(String url, ICallBack callBack, Map<String, String> paras) throws Exception {
		RequestBuilder requestBuilder = RequestBuilder.post().setUri(url);
		if (paras != null) {
			for (Entry<String, String> entry : paras.entrySet()) {
				requestBuilder.addParameter(entry.getKey(), entry.getValue());
			}
		}
		CloseableHttpResponse initResponse = httpclient.execute(requestBuilder.build());
		try {
			StatusLine line = initResponse.getStatusLine();
			if (line.getStatusCode() == 302) {
				Header header = initResponse.getFirstHeader("location");
				post(header.getValue(), callBack);
				return;
			}
			HttpEntity entity = initResponse.getEntity();
			callBack.callBack(entity);
		} finally {
			initResponse.close();
		}
	}

	public void post(String url, Map<String, String> paras) throws Exception {
		post(url, new ICallBack() {
			@Override
			public void callBack(HttpEntity obj) {
				try {
					System.out.println(EntityUtils.toString(obj));
				} catch (Exception e) {
				}
			}
		}, paras);
	}

	public void post(String url) throws Exception {
		post(url, new ICallBack() {
			@Override
			public void callBack(HttpEntity obj) {
				try {
					System.out.println(EntityUtils.toString(obj));
				} catch (Exception e) {
				}
			}
		}, null);
	}

	public void post(String url, ICallBack callBack) throws Exception {
		post(url, callBack, null);
	}

	public List<Cookie> getCookies() {
		return cookieStore.getCookies();
	}

	public void setCookie(String key, String val) {
		BasicClientCookie cookie = new BasicClientCookie(EscapeUnescape.unescape(key), EscapeUnescape.unescape(val));
		cookie.setDomain("kyfw.12306.cn");
		cookieStore.addCookie(cookie);
	}
}
