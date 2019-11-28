package com.example.demo.mq;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author longzhonghua
 * @data 2/21/2019 1:47 PM
 */

@Component
public class TopicSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() {
        String context = "topic";
        System.out.println("Sender : " + context);
        this.amqpTemplate.convertAndSend("topicExchange", "topic.1", context);
    }

    public void send2() {
        String context = "topic 2";
        System.out.println("Sender : " + context);
        this.amqpTemplate.convertAndSend("topicExchange", "topic.a", context);
    }

    public void send3() {
        String context = "topic3";
        System.out.println("Sender : " + context);
        this.amqpTemplate.convertAndSend("topicExchange", "topic.b", context);
    }

}