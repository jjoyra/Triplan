package com.triplan.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.triplan.expcetion.UnAuthorizedException;
import com.triplan.member.model.service.JwtService;

@Component
public class JwtInerceptor implements HandlerInterceptor {
	
	private static final String HEADER_AUTH = "auth-token";
	
	@Autowired
	private JwtService jwtService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		final String token = request.getHeader(HEADER_AUTH);
		
		if (token != null && jwtService.checkToken(token)) {
			System.out.println("토큰 사용 가능 : " + token);
			return true;
		} else {
			throw new UnAuthorizedException();
		}
	}

}
