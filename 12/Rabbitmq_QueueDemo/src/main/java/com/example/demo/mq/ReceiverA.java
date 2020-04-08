package com.example.demo.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author longzhonghua
 * @data 2019/02/03 11:07
 */
@Component
@RabbitListener(queues = "Queue1")//监听QueueHello的消息队列
public class ReceiverA {
    @RabbitHandler//@RabbitHandler来实现具体消费
    public void QueueReceiver(String Queue1) {
        System.out.println("Receiver A: " + Queue1);
    }

}