package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: HelloWorldHandler
 * Author:   longzhonghua
 * Date:     2019/4/27 12:27
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Component
public class HelloWorldHandler {
    public Mono<ServerResponse> sayHelloWorld(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(Mono.just("This is WebFlux demo"), String.class);
    }

}
