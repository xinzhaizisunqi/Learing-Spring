package com.sunqi.business2server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import com.sunqi.ouath2.config.ResServerConfig;

/**
 * @author: 林塬
 * @date: 2018/1/11
 * @description: 资源服务器访问权限配置
 */
@Configuration
public class WebSecurityConfig extends ResServerConfig{

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
        .exceptionHandling().authenticationEntryPoint(new MyAuthenticationEntryPoint())
        .and()
        .authorizeRequests()
                //访问受保护资源 /res 的要求：客户端 Scope 为 read，用户本身角色为 USER
                .antMatchers("/res2")
                .access("#oauth2.hasScope('app') and hasRole('USER')");
    }

}
