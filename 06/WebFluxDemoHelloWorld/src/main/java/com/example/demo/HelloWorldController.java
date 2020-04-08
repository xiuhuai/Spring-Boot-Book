package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: HelloWorldController
 * Author:   longzhonghua
 * Date:     2019/4/27 11:45
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@RestController
public class HelloWorldController {
    @GetMapping("/helloworld")
    public Mono<String> helloworld(){
        return Mono.just("This is WebFlux demo");
    }

}
