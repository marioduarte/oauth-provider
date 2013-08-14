package com.ibm.sbt.service.proxy;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.client.RestOperations;

public class ProxyService {
	private String baseUrl;
	private RestOperations restTemplate;
	
	
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = addTraillingSlash(baseUrl);
	}
	
	public void setRestTemplate(OAuth2RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public String get(String urlPath) throws Exception {
		String url = baseUrl + urlPath;
		return restTemplate.getForObject(url, String.class);
	}
	
	public ResponseEntity<String> getEntity(String urlPath) throws Exception {
		String url = baseUrl + urlPath;
		return restTemplate.getForEntity(url, String.class);
	}
	
	public void getAccessToken() {
		((OAuth2RestTemplate)restTemplate).getAccessToken();
	}
	
	public String retrieveAccessToken() {
		return ((OAuth2RestTemplate)restTemplate).getAccessToken().getValue();
	}
	
	private String addTraillingSlash(String url) {
		if(url.charAt(url.length()-1) != '/') {
			return url+'/';
		}
		else {
			return url;
		}
	}
	
	public OAuth2RestTemplate getRestTemplate() {
		return (OAuth2RestTemplate)restTemplate;
	}
}
