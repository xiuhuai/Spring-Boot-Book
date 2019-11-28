package com.example.demo.config;
 
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */

@Configuration
//过时可以用WebMvcConfigurer
public class WebMvcConfig extends WebMvcConfigurerAdapter{
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //设置登录处理操作
    	registry.addViewController("/home").setViewName("springsecurity/home");
    	registry.addViewController("/").setViewName("springsecurity/welcome");
    	registry.addViewController("/hello").setViewName("springsecurity/welcome");
    	registry.addViewController("/login").setViewName("springsecurity/login");
    	
 registry.addViewController("/json/").setViewName("example/jsondemo01");
   
        
    }

}
