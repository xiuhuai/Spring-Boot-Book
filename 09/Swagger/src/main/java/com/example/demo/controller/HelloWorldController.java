package com.example.demo.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @RequestMapping("/hello")
    public String hello() throws Exception {
        return "HelloWorld ,Spring Boot!";
    }

    /*查看地址更改为：http://localhost:8080/swagger/index.html*/
    @ApiOperation(value = "删除文章", notes = "根据URL的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Long")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String del(@PathVariable("id") long id) {
        //articleRepository.deleteById(id);
        return "success";
    }

}
