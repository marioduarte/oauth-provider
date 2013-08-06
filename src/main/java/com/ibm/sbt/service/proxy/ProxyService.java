package com.ibm.sbt.service.proxy;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.client.RestOperations;

public class ProxyService {
	private String baseUrl;
	private RestOperations restTemplate;
	
	
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	public void setRestTemplate(OAuth2RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public String get(String urlPath) throws Exception {
		String url = baseUrl + urlPath;
		return restTemplate.getForObject(url, String.class);
	}
	
	public String getToken() {
		return ((OAuth2RestTemplate)restTemplate).getAccessToken().getValue();
	}
}
