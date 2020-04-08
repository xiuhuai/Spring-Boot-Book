package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: WebMvcConfig
 * Author:   longzhonghua
 * Date:     2019/5/7 12:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //设置登录处理操作
        registry.addViewController("/home").setViewName("springsecurity/home");
        registry.addViewController("/").setViewName("springsecurity/welcome");

        registry.addViewController("/login").setViewName("springsecurity/login");

    }
}
