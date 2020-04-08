package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
public class MyAnnotationDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyAnnotationDemoApplication.class, args);
	}
}
