package com.example.demo.Servlet.Filter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("test")
public class TestController {
	
	/**
	 * 测试get方法
	 * @param userName
	 * @return
	 */
	@RequestMapping(value="get",method = RequestMethod.GET)
	public String testGet(String userName){
		System.out.println("get使用参数："+userName);
		return userName;
	}
	
	/**
	 * 测试post方法
	 * @param userName
	 * @return
	 */
	@RequestMapping(value="post",method = RequestMethod.POST)
	public String testPost(String userName){
		System.out.println("post使用参数："+userName);
		return userName;
	}
}