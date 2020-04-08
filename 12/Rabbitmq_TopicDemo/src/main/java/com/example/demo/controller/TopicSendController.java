package com.example.demo.controller;


import com.example.demo.mq.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author longzhonghua
 * @data 2/21/2019 1:50 PM
 */
@RestController
public class TopicSendController {
    @Autowired
    private TopicSender sender;

   @GetMapping("topicsend")
    public void topic() throws Exception {
        sender.send();
    }

    @GetMapping("topicsend2")
    public void topic1() throws Exception {
        sender.send2();
    }

    @GetMapping("topicsend3")
    public void topic2() throws Exception {
        sender.send3();
    }
}
