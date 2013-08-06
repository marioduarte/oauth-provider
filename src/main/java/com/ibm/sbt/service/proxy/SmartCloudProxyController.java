package com.ibm.sbt.service.proxy;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibm.sbt.model.User;

@Controller
@RequestMapping("proxy/smartcloud")
public class SmartCloudProxyController {

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
	public String proxyRequest(@PathVariable("path") String path) throws Exception {
		return proxyService.get(path);
	}
	
	
	@RequestMapping("token")
	@ResponseBody
	public String getToken() {
		return proxyService.getToken();
	}
	
	public void setProxyService(ProxyService proxyService) {
		this.proxyService = proxyService;
	}
}
