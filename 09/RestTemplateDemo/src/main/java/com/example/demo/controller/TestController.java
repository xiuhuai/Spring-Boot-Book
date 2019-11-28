package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * Author:   longzhonghua
 * Date:     4/8/2019 7:58 AM
 */
@RestController
public class TestController {

    @RequestMapping(value = "/getparameter", method = RequestMethod.GET)
    public User getparameter(User user) {
        return user;
    }

    @RequestMapping(value = "/getuser1", method = RequestMethod.GET)
    public User user1() {
        return new User(1, "zhonghua");
    }

    @RequestMapping(value = "/postuser", method = RequestMethod.POST)
    public User postUser(User user) {
        System.out.println("name:" + user.getName());
        System.out.println("id:" + user.getId());
        return user;
    }


    @RequestMapping(path = "success")
    public String loginSuccess(String name) {
        return "welcome " + name;
    }

    @RequestMapping(value = "/post", method =  RequestMethod.POST)
    public String post(HttpServletRequest request, @RequestParam(value = "name", required = false) String name,
                       @RequestParam(value = "password", required = false) String password,  @RequestParam(value = "id", required = false) Integer id) {
        return "redirect:/success?name=" + name + "&id=" + id + "&status=success";
    }

}
