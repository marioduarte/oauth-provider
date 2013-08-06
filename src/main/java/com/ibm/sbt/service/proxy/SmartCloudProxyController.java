package com.ibm.sbt.service.proxy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("proxy/smartcloud")
public class SmartCloudProxyController extends ProxyController {

}
/*
public class SmartCloudProxyController {
	protected final Log logger = LogFactory.getLog(getClass());
	
	private ProxyService proxyService;
	
	@RequestMapping("token")
	@ResponseBody
	public void getToken() {
		proxyService.getOauth2Token();
	}
	
	@RequestMapping(value="/**", method=RequestMethod.GET)
	@ResponseBody
	public String proxyRequest(HttpServletRequest request) throws Exception {
		String path = extractPathFromPattern(request);
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
*/
