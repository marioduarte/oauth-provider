package com.ibm.sbt.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CORSFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) 
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
	 	response.setHeader("Access-Control-Allow-Origin", "*");
	 	response.setHeader("Access-Control-Allow-Headers", "Authorization");

	 	if (! "OPTIONS".equals(request.getMethod())) {
	 		// pass the request along the filter chain
			chain.doFilter(request, response);
	 	}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
