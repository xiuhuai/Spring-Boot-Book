package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;


@ComponentScan(basePackages = {"com.example.*"})
@EnableReactiveMongoRepositories
@SpringBootApplication
public class WebFluxMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebFluxMongodbApplication.class, args);
	}

}
