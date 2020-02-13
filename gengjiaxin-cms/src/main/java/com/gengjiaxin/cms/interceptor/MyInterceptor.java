package com.gengjiaxin.cms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gengjiaxin.cms.domain.User;

public class MyInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(request.getSession().getAttribute("user")!=null) {
			User user = (User) request.getSession().getAttribute("user");
			if("1".equals(user.getRole())) {
				System.out.println("1111111111");
				System.out.println(request.getRequestURI()+"进入后台管理"+System.currentTimeMillis());
				response.sendRedirect("/admin");
			}else {
				response.sendRedirect("/index");
			}
		}else {
			response.sendRedirect("login.jsp");
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
