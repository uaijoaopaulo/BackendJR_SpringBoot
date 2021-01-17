package com.example.backendJR;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class BackendJrApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendJrApplication.class, args);
	}

}
