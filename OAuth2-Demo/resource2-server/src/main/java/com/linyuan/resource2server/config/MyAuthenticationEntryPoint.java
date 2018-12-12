package com.linyuan.resource2server.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint{

	 @Override
	    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		 httpServletResponse.setCharacterEncoding("utf-8");
		 httpServletResponse.setContentType("text/javascript;charset=utf-8");
		 Map<String,String> map = new HashMap<>();
		 map.put("error_code", "401");
		 map.put("messgae", "无权限访问");
		 JSONObject json =new JSONObject(map);
		 httpServletResponse.getWriter().print(json);

	    }


}
