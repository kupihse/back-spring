package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(BackendApplication.class, args);
	}

//	@Bean
//	@ConfigurationProperties(prefix="spring.datasource")
//	public DataSource dataSource() {
//    return DataSourceBuilder.create().build();
//	}
}
