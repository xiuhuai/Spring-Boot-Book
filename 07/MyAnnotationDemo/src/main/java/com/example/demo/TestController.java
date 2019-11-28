package com.example.demo;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: TestController
 * Author:   longzhonghua
 * Date:     5/19/2019 8:50 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@RestController
@EnableAspectJAutoProxy
public class TestController {
    @RequestMapping("/")
    @MyTestAnnotation("测试Annotation")
    public void testAnnotation() {
        System.err.println("测试自定义注解");
    }

}
