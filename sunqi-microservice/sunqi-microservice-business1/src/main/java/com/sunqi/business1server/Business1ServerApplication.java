package com.sunqi.business1server;
import feign.RequestInterceptor;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.sunqi.ouath2.annotion.EnableRemoteTokenService;

@SpringBootApplication
//@EnableResJWTTokenStore //OAuth2 使用 JWT 解析令牌
@EnableRemoteTokenService
@EnableEurekaClient
@EnableFeignClients
public class Business1ServerApplication {

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(Business1ServerApplication.class, args);
	}
	
	@Bean
	public RequestInterceptor headerInterceptor() {
	    return template -> {
	        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	        HttpServletRequest request = attributes.getRequest();
	        Enumeration<String> headerNames = request.getHeaderNames();
	        if (headerNames != null) {
	            while (headerNames.hasMoreElements()) {
	                String name = headerNames.nextElement();
	                String values = request.getHeader(name);
	                template.header(name, values);
	            }
	        }
	    };
	}
}
