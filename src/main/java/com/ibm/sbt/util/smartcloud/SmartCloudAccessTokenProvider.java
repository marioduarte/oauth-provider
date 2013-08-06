package com.ibm.sbt.util.smartcloud;

import java.util.Arrays;

import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.implicit.ImplicitAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;

public class SmartCloudAccessTokenProvider extends AccessTokenProviderChain {

	public SmartCloudAccessTokenProvider() {
		super(Arrays.<AccessTokenProvider> asList(
				new SmartCloudAuthorizationCodeAccessTokenProvider(), new ImplicitAccessTokenProvider(),
				new ResourceOwnerPasswordAccessTokenProvider(), new ClientCredentialsAccessTokenProvider()));
	}
}
