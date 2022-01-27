package com.jonathan.fintech.email.client;

import com.jonathan.fintech.email.client.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient
public class EmailClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailClientApplication.class, args);
	}

}