package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@SpringBootApplication
public class Jsr250DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Jsr250DemoApplication.class, args);
	}

}
