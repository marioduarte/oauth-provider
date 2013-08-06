package com.ibm.sbt.util.smartcloud;

import org.springframework.security.oauth2.client.token.AccessTokenRequest;

public class SmartCloudAuthorizationCodeResourceDetails extends org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails {
	
	public String getRedirectUri(AccessTokenRequest request) {
		String redirectUri = request.getFirst("callback_uri");

		if (redirectUri == null && request.getCurrentUri() != null && isUseCurrentUri()) {
			redirectUri = request.getCurrentUri();
		}

		if (redirectUri == null && getPreEstablishedRedirectUri() != null) {
			// Override the redirect_uri if it is pre-registered
			redirectUri = getPreEstablishedRedirectUri();
		}

		return redirectUri;
	}
}
