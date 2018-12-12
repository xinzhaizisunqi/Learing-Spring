package com.sunqi.business2server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.sunqi.ouath2.annotion.EnableRemoteTokenService;

@SpringBootApplication
@EnableRemoteTokenService
@EnableEurekaClient
public class Bussiness2ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Bussiness2ServerApplication.class, args);
	}
}
