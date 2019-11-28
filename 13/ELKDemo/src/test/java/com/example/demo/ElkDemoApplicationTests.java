package com.example.demo;




import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElkDemoApplicationTests {
	// 定义一个全局的记录器，通过LoggerFactory获取
	private final static Logger log = LoggerFactory.getLogger(Test.class);

	@Before
	public void setUp() {
	}
	@Test
	public void test() {
		log.trace("trace 成功了");
		log.debug("debug 成功了");
		log.info("info 成功了");
		log.warn("warn 成功了");
		log.error("error 成功了");
	}

	@Test
	public void contextLoads() {
	}

}
