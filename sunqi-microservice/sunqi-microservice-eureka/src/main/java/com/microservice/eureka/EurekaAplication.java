package com.microservice.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaAplication {
	public static void main(String[] args) {
		SpringApplication.run(EurekaAplication.class, args);
	}

}
