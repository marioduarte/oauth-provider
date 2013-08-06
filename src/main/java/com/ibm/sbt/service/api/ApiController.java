package com.ibm.sbt.service.api;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import com.ibm.sbt.model.User;

@Controller
@RequestMapping("api")
public class ApiController {

	protected final Log logger = LogFactory.getLog(getClass());
	
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
	
	@RequestMapping("test1/**")
	public String test1() {
		logger.error("TEST1: **");
		return "";
	}
	
	@RequestMapping("test2/{path:.*}")
	public String test2(@PathVariable("path") String path) {
		logger.error("TEST2: "+path);
		return "";
	}
	
	@RequestMapping("test3/**")
	public String test3(HttpServletRequest request) {
//		String path = (String)request.getAttribute( HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE );
		logger.error("TEST3: "+extractPathFromPattern(request));
		return "";
	}
	
	private static String extractPathFromPattern(final HttpServletRequest request){
	    String path = (String) request.getAttribute(
	            HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
	    String bestMatchPattern = (String ) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);

	    AntPathMatcher apm = new AntPathMatcher();
	    String finalPath = apm.extractPathWithinPattern(bestMatchPattern, path);

	    return finalPath;

	}
}
