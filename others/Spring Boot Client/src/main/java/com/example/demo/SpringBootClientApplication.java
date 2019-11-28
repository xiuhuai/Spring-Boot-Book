package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@SpringBootApplication
public class SpringBootClientApplication {
	@RequestMapping("/hello")
	public String sayHello(String name){
		return "hello "+name;
	}

	@Scheduled(cron="0 0 10,14,16 * * ?")
	public void task() {
		System.out.println("我执行了....");

	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootClientApplication.class, args);
	}

}
