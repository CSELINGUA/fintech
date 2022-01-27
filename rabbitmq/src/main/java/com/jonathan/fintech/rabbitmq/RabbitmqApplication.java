package com.jonathan.fintech.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient
public class RabbitmqApplication {
	public static void main(String[] args) {
		SpringApplication.run(RabbitmqApplication.class, args);
	}
}