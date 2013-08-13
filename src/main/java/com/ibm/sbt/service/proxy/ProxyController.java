package com.ibm.sbt.service.proxy;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

public class ProxyController {
	protected final Log logger = LogFactory.getLog(getClass());
	
	private ProxyService proxyService;
	

	@RequestMapping("token")
	@ResponseBody
	public void getAccessToken(HttpServletResponse response, 
			@RequestParam(value="redirectUrl", required=false) String redirectUrl) throws IOException {
		proxyService.getAccessToken();
		
		if(redirectUrl != null && redirectUrl != "") {
			response.sendRedirect(redirectUrl + "#oauth2_service_token_received=true");
		}
	}
	
	@RequestMapping(value="/**", method=RequestMethod.GET)
	@ResponseBody
	public String proxyRequest(HttpServletRequest request) throws Exception {
		String path = extractPathFromPattern(request);
		
		logger.info("XXXXX1: "+proxyService);
		logger.info("XXXXX2: "+proxyService.getRestTemplate());
		logger.info("XXXXX3: "+proxyService.getRestTemplate().getOAuth2ClientContext());
		logger.info("XXXXX4: "+proxyService.getRestTemplate().getOAuth2ClientContext().getAccessToken());
		logger.info("XXXXX5: "+proxyService.getRestTemplate().getOAuth2ClientContext().getAccessToken().getValue());
		
		return proxyService.get(path);
	}
	
	private static String extractPathFromPattern(final HttpServletRequest request){
	    String path = (String) request.getAttribute(
	            HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
	    String bestMatchPattern = (String ) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);

	    AntPathMatcher apm = new AntPathMatcher();
	    String finalPath = apm.extractPathWithinPattern(bestMatchPattern, path);

	    return finalPath;
	}
	
	public void setProxyService(ProxyService proxyService) {
		this.proxyService = proxyService;
	}
}
