package com.example.demo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class Hello {
    @RequestMapping("/hello")
    public String hello()  throws Exception{
        return "Hello ,Spring Boot!";
    }
}
