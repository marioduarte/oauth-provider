package com.ibm.sbt.service;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.client.RestOperations;

public class ProxyService {
	private String baseUrl;
	private RestOperations proxyAppRestTemplate;
	
	
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	public void setProxyAppRestTemplate(OAuth2RestTemplate proxyAppRestTemplate) {
		this.proxyAppRestTemplate = proxyAppRestTemplate;
	}
	
	public String getToken() {
		return ((OAuth2RestTemplate)proxyAppRestTemplate).getAccessToken().getValue();
	}
}
