package com.elearntez.springmvc.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TimeLoggingInterceptor implements HandlerInterceptor{
	
	@Autowired
	private ApplicationContext context;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("context "+context);
		System.out.println("context "+context);
		System.out.println("preHandle Request URL::" + request.getRequestURL().toString()
				+ ":: Start Time=" + System.currentTimeMillis());
		
		request.setAttribute("startTime", System.currentTimeMillis());
		
		//if returned false, we need to make sure 'response' is sent
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		Long requestMethodExecutionStartTime = new Long(request.getAttribute("startTime").toString());
		
		
		System.out.println("postHandle Request URL::" + request.getRequestURL().toString()
				+ " Sent to Handler :: Current Time=" + ( System.currentTimeMillis()- requestMethodExecutionStartTime));
		//we can add attributes in the modelAndView and use that in the view page
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		System.out.println("afterCompletion Request URL::" + request.getRequestURL().toString()
				+ ":: End Time=" + System.currentTimeMillis());
		
		System.out.println("Request URL::" + request.getRequestURL().toString()
				+ ":: Time Taken=" + (System.currentTimeMillis() - startTime));
	}
	
}
