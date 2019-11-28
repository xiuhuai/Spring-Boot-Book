package com.example.demo.controller;


import com.example.demo.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author longzhonghua
 * @createdata 3/18/2019 10:25 PM
 * @description
 */
@RestController
public class TestController {
    @RequestMapping("/BusinessException")
    public String testResponseStatusExceptionResolver(@RequestParam("i") int i){
        if (i==0){
            throw new BusinessException(600,"自定义业务错误");
        }
              return "success";
    }

}
