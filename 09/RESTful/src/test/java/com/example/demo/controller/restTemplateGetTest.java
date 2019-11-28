package com.example.demo.controller;

import com.example.demo.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: restTemplateGetTest
 * Author:   longzhonghua
 * Date:     4/7/2019 10:53 PM
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class restTemplateGetTest {
    @Test
    public void restTemplateGetTest(){
        RestTemplate restTemplate = new RestTemplate();
        Article  article = restTemplate.getForObject("http://localhost:8080/article/1"
                , Article.class);
        System.out.println(article);
    }

}