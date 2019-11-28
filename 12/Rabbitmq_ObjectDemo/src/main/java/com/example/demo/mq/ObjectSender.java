package com.example.demo.mq;

/**
 * @author longzhonghua
 * @data 2/21/2019 1:20 PM
 */

import com.example.demo.model.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(User user) {
        System.out.println("Sender object: " + user.toString());
        this.amqpTemplate.convertAndSend("object", user);
    }

}