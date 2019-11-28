package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@ServletComponentScan
@SpringBootApplication
public class ServletDemoApplication {

    /**
     * Description: 注册一个bean 对象
     */
    @Bean
    public ServletRegistrationBean ServletDemo01() {
/**
 * Description: 注册ServletRegistrationBean获得相应的控制
 */

        return new ServletRegistrationBean(new ServletDemo01(), "/ServletDemo01/*");
    }
    public static void main(String[] args) {
        SpringApplication.run(ServletDemoApplication.class, args);
    }

}
