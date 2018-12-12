package com.sunqi.ouath2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @author: 林塬
 * @date: 2018/1/20
 * @description: OAuth2 资源服务器配置类
 */
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public abstract class ResServerConfig extends ResourceServerConfigurerAdapter {
	//这地方不需要注入和设置到ResourceServerSecurityConfigurer 只需要RemoteTokenService 里面
	//创建这个bean ResourceServerTokenServices  到容器中就即可
    @Autowired(required = false)
    private ResourceServerTokenServices remoteTokenServices;

    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;

    @Bean
    @Qualifier("authorizationHeaderRequestMatcher")
    public RequestMatcher authorizationHeaderRequestMatcher() {
        return new RequestHeaderRequestMatcher("Authorization");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .exceptionHandling()
           .and()
                .headers()
                .frameOptions()
                .disable()
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .requestMatcher(authorizationHeaderRequestMatcher());
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);

        resources.resourceId(oAuth2ClientProperties.getClientId());
    
      /*  if (Objects.nonNull(remoteTokenServices)) {
            resources.tokenServices(remoteTokenServices);
        }*/
    }
}