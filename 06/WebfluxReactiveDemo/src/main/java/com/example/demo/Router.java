package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: Router
 * Author:   longzhonghua
 * Date:     2019/4/27 12:28
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Configuration
public class Router {
    @Autowired
    private HelloWorldHandler helloWorldHandler;
    @Bean
    public RouterFunction<ServerResponse> getString(){
        return route(GET("/helloworld"),req->helloWorldHandler.sayHelloWorld(req));
    }

}
