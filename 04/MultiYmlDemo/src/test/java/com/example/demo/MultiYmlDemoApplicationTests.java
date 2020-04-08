package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiYmlDemoApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Value("${myenvironment.name}")
	private String name;
	@Test
	public void getMyEnvironment() {
		System.out.println(name);

	}
}
