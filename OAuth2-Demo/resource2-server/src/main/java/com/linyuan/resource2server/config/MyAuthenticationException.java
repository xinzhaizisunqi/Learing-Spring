package com.linyuan.resource2server.config;

import org.springframework.security.core.AuthenticationException;

public class MyAuthenticationException extends AuthenticationException{

	public MyAuthenticationException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
