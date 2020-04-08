package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:   longzhonghua
 * Date:     3/21/2019 9:38 PM
 */

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(String name){
        return "hello "+name;
    }
   }

