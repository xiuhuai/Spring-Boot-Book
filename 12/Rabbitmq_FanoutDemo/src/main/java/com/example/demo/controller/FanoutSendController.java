package com.example.demo.controller;

import com.example.demo.mq.FanoutSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author longzhonghua
 * @data 2/21/2019 2:13 PM
 */
@RestController
public class FanoutSendController {
    @Autowired
    private FanoutSender sender;

    @GetMapping("/FanoutSend")
    public void fanoutSender() throws Exception {
        sender.send();
    }

}
