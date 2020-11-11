package com.bitcamp.test.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	//컨트롤러가 호출되기 전에 실행된다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		//로그인 여부를 확인하여 로그인 된 경우 계속 실행하고 로그인 안된 경우 컨트로럴 실행을 중지한다.
		HttpSession ses = request.getSession();
		
		String logStatus = (String)ses.getAttribute("logStatus");
		//로그인이 안 된 경우 로그인포믕로 보냄. 현재 진행을 중단시킴.
		if(logStatus==null|| !logStatus.equals("Y")) {
			response.sendRedirect(request.getContextPath()+"/login");
			return false;
		}
		return true;
	}
	//컨트롤러가 실행 한 다음에 view페이지로 이동하기 전에 호출된다.
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modalAndView) throws Exception{
		
	}
	//컨트롤러가 실행후 호출된다.
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception{
		
	}
}
