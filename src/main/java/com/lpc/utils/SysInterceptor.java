package com.lpc.utils;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SysInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session=request.getSession();
		Object obj=session.getAttribute("userOnLogin");
		if(obj==null){
			String path=request.getContextPath();
			session.setAttribute("error", "未登陆，请先登陆");
			response.sendRedirect(path+"/login");
			return false;
		}
		return true;
	}

}
