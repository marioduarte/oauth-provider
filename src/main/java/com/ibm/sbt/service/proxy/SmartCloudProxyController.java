package com.ibm.sbt.service.proxy;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import com.ibm.sbt.model.User;

@Controller
@RequestMapping("proxy/smartcloud")
public class SmartCloudProxyController {

	protected final Log logger = LogFactory.getLog(getClass());
	
	private ProxyService proxyService;
	
	@RequestMapping("user")
	@ResponseBody
	public User randomPerson() {
		org.springframework.security.core.userdetails.User springUser = 
				(org.springframework.security.core.userdetails.User)
				SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String name = springUser.getUsername();
		
	    User user = new User();
	    user.setName(name);
	    return user;
	}
	
	@RequestMapping(value="/**", method=RequestMethod.GET)
	@ResponseBody
	public String proxyRequest(HttpServletRequest request) throws Exception {
		String path = extractPathFromPattern(request);
		logger.info("Path: "+path);
		return proxyService.get(path);
	}
	
	
	@RequestMapping("token")
	@ResponseBody
	public String getToken() {
		return proxyService.getToken();
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
