package com.ibm.sbt.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
 
	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public String printWelcome(Model model) {
		model.addAttribute("message", "Welcome!!!");
		return "SimplePage";
	}
	
	@RequestMapping(value="/hello", method = RequestMethod.GET)
	public ModelAndView helloWorld() {
		 
        String message = "Hello World, Spring 3.0!";
        return new ModelAndView("SimplePage", "message", message);
    }
	
	@RequestMapping(value="/name", method = RequestMethod.GET)
	public ModelAndView name() {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String name = user.getUsername(); //get logged in username
        return new ModelAndView("SimplePage", "message", name);
    }
}