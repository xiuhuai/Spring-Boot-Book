package com.example.demo.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author longzhonghua
 * @data 2019/02/03 11:06
 * rabbitTemplate是springboot 提供的默认实现
 */
@Component
public class SenderA {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String context) {
        System.out.println("Sender : " + context);
        //使用AmqpTemplate将消息发送到消息队列QueueHello中去
        this.rabbitTemplate.convertAndSend("Queue1", context);
    }

}