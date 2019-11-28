package com.example.demo.controller.member;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author longzhonghua
 * @data 2019/02/03 11:07
 */
@Component
@RabbitListener(queues = "reg_email")//监听消息队列
public class RegEmailQueueReceiver {

    @RabbitHandler//@RabbitHandler来实现具体消费
    public void QueueReceiver(String reg_email) {

        try {
            //send email
            System.out.println("Receiver : " + reg_email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}