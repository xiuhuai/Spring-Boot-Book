package com.example.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class HelloWorldController {
    @ApiOperation(value = "hello", notes = "notes ")
    @RequestMapping("/hello")
    public String hello() throws Exception {
        return "HelloWorld ,Spring Boot!";
    }
    //使用该注解忽略这个API
    @ApiIgnore
    @RequestMapping(value = "/ignoreApi")
    public String  ignoreApi() {
        return "HelloWorld ,Spring Boot!";
    }

/*
@ApiOperation(value = "删除文章", notes = "根据URL的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Long")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String del(@PathVariable("id") long id) {
        //articleRepository.deleteById(id);
        return "success";
    }*/

}
