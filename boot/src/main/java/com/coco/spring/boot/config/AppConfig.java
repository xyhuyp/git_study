package com.coco.spring.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.coco.spring.boot.web.URLS_12306;
import com.coco.spring.boot.web.https.Http12306Client;

@Configuration
public class AppConfig{

	@Bean
	public Http12306Client httpClient(){
		Http12306Client client =  new Http12306Client();
		try {
			client.get(URLS_12306.LOGIN_INIT);
		} catch (Exception e) {
		}
		return client;
	}
	
}
