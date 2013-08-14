package com.ibm.sbt.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.ClientTokenServices;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public class InMemoryClientTokenServices implements ClientTokenServices {

	private static final Map<String, Map<String, OAuth2AccessToken>> USER_TO_RESOURCE_TO_TOKEN = 
			new ConcurrentHashMap<String, Map<String, OAuth2AccessToken>>();
	
	@Override
	public OAuth2AccessToken getAccessToken(OAuth2ProtectedResourceDetails resource, Authentication authentication) {
	    if (authentication != null && authentication.isAuthenticated()) {
	      Map<String, OAuth2AccessToken> resourceMap = USER_TO_RESOURCE_TO_TOKEN.get(authentication.getName());
	      return resourceMap == null ? null : resourceMap.get(resource.getId());
	    }

	    return null;
	}

	@Override
	public void saveAccessToken(OAuth2ProtectedResourceDetails resource,
			Authentication authentication, OAuth2AccessToken accessToken) {
		if (authentication != null && authentication.isAuthenticated()) {
			Map<String, OAuth2AccessToken> resourceMap = USER_TO_RESOURCE_TO_TOKEN.get(authentication.getName());
			if (resourceMap == null) {
				resourceMap = new ConcurrentHashMap<String, OAuth2AccessToken>();
				USER_TO_RESOURCE_TO_TOKEN.put(authentication.getName(), resourceMap);
			}
			resourceMap.put(resource.getId(), accessToken);
		}
	}

	@Override
	public void removeAccessToken(OAuth2ProtectedResourceDetails resource, Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()) {
			Map<String, OAuth2AccessToken> resourceMap = USER_TO_RESOURCE_TO_TOKEN.get(authentication.getName());
			if (resourceMap != null) {
				resourceMap.remove(resource.getId());
			}
		}
	}
}
