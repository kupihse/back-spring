package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@PropertySource("classpath:pass.properties")
public class BackendApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(BackendApplication.class, args);
	}
}
