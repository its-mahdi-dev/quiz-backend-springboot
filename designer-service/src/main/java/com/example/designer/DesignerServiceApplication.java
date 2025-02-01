package com.example.designer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DesignerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesignerServiceApplication.class, args);
	}

}
