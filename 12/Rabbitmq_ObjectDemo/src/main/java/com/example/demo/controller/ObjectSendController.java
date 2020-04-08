
package com.example.demo.controller;

import com.example.demo.model.User;

import com.example.demo.mq.ObjectSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author longzhonghua
 * @data 2/21/2019 1:26 PM
 */

@RestController
public class ObjectSendController {
    @Autowired
    private ObjectSender objectSender;

    @GetMapping("/sendOject")
    public String sendOjectController(){
        try {
            User user=new User();
            user.setName("longzhiran");
            user.setAge("18");
            objectSender.send(user);
        } catch (Exception e) {
            e.printStackTrace();
            return "执行失败";
        }
        return"执行成功";
    }
}

