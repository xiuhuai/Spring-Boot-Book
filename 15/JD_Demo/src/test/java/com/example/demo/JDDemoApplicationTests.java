package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JDDemoApplicationTests {
	@Autowired
	private AmqpTemplate rabbitTemplate;
	@Test
	public void regQueue() {
		rabbitTemplate.convertAndSend("reg_email", "363694485@qq.com");
	}

}
