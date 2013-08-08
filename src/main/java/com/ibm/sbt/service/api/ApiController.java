package com.ibm.sbt.service.api;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import com.ibm.sbt.model.User;

@Controller
@RequestMapping("api")
public class ApiController {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@RequestMapping("token")
	@ResponseBody
	public void getAccessToken(HttpServletResponse response, @RequestParam(value="redirectUrl", required=false) String redirectUrl) throws IOException {
		if(redirectUrl != null && redirectUrl != "") {
			response.sendRedirect(redirectUrl);
		}
	}
	
	@RequestMapping("token2")
	public String getAccessToken(@RequestParam(value="redirectUrl", required=false) String redirectUrl) throws IOException {
		if(redirectUrl != null && redirectUrl != "") {
			return "redirect:"+redirectUrl;
		}
		return null;
	}
	
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
	
	@RequestMapping("test1")
	@ResponseBody
	public void test1() {
		logger.error("TEST1");
	}
	
	@RequestMapping("test2")
	public String test2() {
		logger.error("TEST2");
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
