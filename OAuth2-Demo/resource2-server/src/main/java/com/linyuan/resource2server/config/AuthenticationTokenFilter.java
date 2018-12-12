package com.linyuan.resource2server.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String authHeader = request.getHeader("Authorization");
		 if (authHeader ==null||authHeader =="") {
			 throw new RuntimeException("Authorization bi xu you");
		 }
		 filterChain.doFilter(request, response);
	}

}
