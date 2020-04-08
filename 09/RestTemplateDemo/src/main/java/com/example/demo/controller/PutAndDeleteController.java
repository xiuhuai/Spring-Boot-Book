package com.example.demo.controller;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Author:   longzhonghua
 * Date:     4/8/2019 10:20 AM
 */
@RestController
public class PutAndDeleteController {
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @RequestMapping("/put")
    public void put() {
        RestTemplate client= restTemplateBuilder.build();
        User user = new User();
        user.setName("hongwei");
        client.put("http://localhost:8080/{1}", user, 7);
    }
    @RequestMapping("/delete")
    public void delete() {
        RestTemplate client= restTemplateBuilder.build();
        client.delete("http://localhost:8080/{1}", 8);
    }

}
