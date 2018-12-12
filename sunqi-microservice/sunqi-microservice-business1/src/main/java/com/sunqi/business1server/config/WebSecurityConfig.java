package com.sunqi.business1server.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import com.sunqi.ouath2.config.ResServerConfig;

/**
 * @author: 林塬
 * @date: 2018/1/18
 * @description: 资源服务器访问权限配置
 */
@Configuration
public class WebSecurityConfig extends ResServerConfig {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/res").access("#oauth2.hasScope('app') and hasRole('USER')");
                //.antMatchers("/res2test").access("#oauth2.hasScope('app') and hasRole('USER')");
        
    }

}
