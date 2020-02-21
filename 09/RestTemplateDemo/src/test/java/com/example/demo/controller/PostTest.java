package com.example.demo.controller;

import com.example.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.junit.Assert.*;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: PostControllerTest
 * Author:   longzhonghua
 * Date:     5/22/2019 2:07 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class PostTest {
    @Autowired
    RestTemplateBuilder restTemplateBuilder;
    RestTemplate restTemplate = new RestTemplate();
    @Test
    public void postForEntity() {
        //RestTemplate client= restTemplateBuilder.build();
// 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("name", "longzhiran");
        paramMap.add("id", 4);
     /*   User user = new User();
        user.setName("hongwei");
        user.setId(4);*/
        //方法的第一参数表示要调用的服务的地址
        //方法的第二个参数表示上传的参数
        //方法的第三个参数表示返回的消息体的数据类型
        ResponseEntity<User> responseEntity = restTemplate.postForEntity("http://localhost:8080/postuser", paramMap, User.class);
        System.out.println( responseEntity.getBody().getName());
    }
    @Test
    public void postForObject() {
        // 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("name", "longzhonghua");
        paramMap.add("id", 4);
        RestTemplate client = restTemplateBuilder.build();
        String response = client.postForObject("http://localhost:8080/postuser", paramMap, String.class);
        System.out.println(response);
    }

    @Test
    public void postForLocation() {
        // 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("name", "longzhonghua");
        paramMap.add("id", 4);

        RestTemplate client = restTemplateBuilder.build();

        URI response = client.postForLocation("http://localhost:8080/post",paramMap);

         System.out.println(response);
    }



    @Test
    public void postForexchange() {
        // 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("name", "longzhonghua");
        paramMap.add("id", 4);
        RestTemplate client = restTemplateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        //headers.set("id", "long");
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(paramMap,headers);
        ResponseEntity<String> response = client.exchange("http://localhost:8080/postuser", HttpMethod.POST,httpEntity,String.class,paramMap);
        System.out.println(response.getBody());
    }
    @Test
    public void put() {
        RestTemplate client= restTemplateBuilder.build();
        User user = new User();
        user.setName("longzhiran");
        client.put("http://localhost:8080/{1}", user, 4);
    }
    @Test
    public void delete() {
        RestTemplate client= restTemplateBuilder.build();
        client.delete("http://localhost:8080/{1}", 4);
    }


}