package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.io.File;


@SpringBootApplication

// Здесь указаны файлы конфигурации
// pass.properties – хранится локально на сервере
@PropertySource("classpath:application.properties")
@PropertySource("classpath:pass.properties")

public class BackendApplication {
	public static void main(String[] args) throws Exception {

		File imDir = new File("/root/imgs");
		if (!imDir.exists()) {
			if (!imDir.mkdir()) {
				System.out.println("Couldn't start");
				return;
			}
		}

		if (!imDir.isDirectory()) {
			System.out.println("Couldn't start, not a directory");
			return;
		}

		SpringApplication.run(BackendApplication.class, args);
	}
}
