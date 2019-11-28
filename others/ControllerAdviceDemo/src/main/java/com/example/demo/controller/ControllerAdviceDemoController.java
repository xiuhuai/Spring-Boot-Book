package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author longzhonghua
 * @createdata 3/18/2019 9:45 PM
 * @description
 */
@RequestMapping("test")
@RestController
public class ControllerAdviceDemoController {
    @GetMapping("/")
    public String testControllerAdvice(){
        return "test";
    }
}
