package com.example.demo.controller.shop;


import com.example.demo.module.mq.CancelOrderSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author longzhonghua
 * @data 2/25/2019 7:35 PM
 */
@RestController
public class CancelOrderController {
    @Autowired
    private CancelOrderSender cancelOrderSender;
    int orderId = 1;

    @GetMapping("/customSend")
    public void send() {
        cancelOrderSender.sendMsg("delay_queue_1", orderId);
    }
}
