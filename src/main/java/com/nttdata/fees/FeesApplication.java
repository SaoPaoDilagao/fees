package com.nttdata.fees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FeesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeesApplication.class, args);
	}

}
