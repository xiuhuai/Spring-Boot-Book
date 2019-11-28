package com.example.demo.module.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author longzhonghua
 * @data 2/25/2019 7:40 PM
 */
@Component
public class CancelOrderReceiver {
    @RabbitListener(queues = "delay_queue_1")
    public void receive(String msg) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()));
        System.out.println("Receiver :执行取消订单"+msg);
           }
}
