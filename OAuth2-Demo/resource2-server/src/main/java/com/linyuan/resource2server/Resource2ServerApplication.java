package com.linyuan.resource2server;

import com.linyuan.oauth2config.config.annotation.EnableRemoteTokenService;
import com.linyuan.oauth2config.config.annotation.EnableResJWTTokenStore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableRemoteTokenService
@EnableEurekaClient
public class Resource2ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Resource2ServerApplication.class, args);
	}
}
