package com.example.demo.mq;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * @author longzhonghua
 * @data 2/21/2019 1:49 PM
 */
@Component
@RabbitListener(queues = "topic.b")
public class TopicReceiverB{
    @RabbitHandler
    public void process(String msg) {
        System.out.println("Topic ReceiverB: " + msg);
    }
}
