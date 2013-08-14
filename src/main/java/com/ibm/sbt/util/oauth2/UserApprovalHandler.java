package com.ibm.sbt.util.oauth2;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.approval.TokenServicesUserApprovalHandler;

public class UserApprovalHandler  extends TokenServicesUserApprovalHandler {
	private Collection<String> autoApproveClients = new HashSet<String>();
	private boolean useTokenServices = true;

	public void setUseTokenServices(boolean useTokenServices) {
		this.useTokenServices = useTokenServices;
	}

	public void setAutoApproveClients(Collection<String> autoApproveClients) {
		this.autoApproveClients = autoApproveClients;
	}

	@Override
	public boolean isApproved(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
		if (useTokenServices && super.isApproved(authorizationRequest, userAuthentication)) {
			return true;
		}

		if (!userAuthentication.isAuthenticated()) {
			return false;
		}

		return authorizationRequest.getResponseTypes().contains("token") && 
				autoApproveClients.contains(authorizationRequest.getClientId());
	}
}
