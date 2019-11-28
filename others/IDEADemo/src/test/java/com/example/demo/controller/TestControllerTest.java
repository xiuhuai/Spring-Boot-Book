package com.example.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: TestControllerTest
 * Author:   longzhonghua
 * Date:     5/27/2019 9:34 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestControllerTest {

    @Test
    public void test() {
        String jsonString = "{ \"member\": [\n" +
                "  { \"name\":\"龙毅\" , \"message\":\"敢于承担责任,就是一种阔达\" },\n" +
                "  { \"name\":\"龙俊涛\" , \"message\":\"人生需要大智慧\" }\n" +
                "   ]\n" +
                "}";
        System.out.println(jsonString);
    }
}