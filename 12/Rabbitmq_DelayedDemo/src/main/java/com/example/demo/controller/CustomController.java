package com.example.demo.controller;

import com.example.demo.mq.CustomSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author longzhonghua
 * @data 2/25/2019 7:35 PM
 */
@RestController
public class CustomController {
  @Autowired
  private CustomSender customSender;

    @GetMapping("/customSend")

    public void send() {
        customSender.sendMsg("delay_queue_1","支付超时！");
    }
}
