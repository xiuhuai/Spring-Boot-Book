package com.example.demo.controller;

import com.example.demo.mq.SenderA;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.retry.backoff.Sleeper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: QueueSendTest
 * Author:   longzhonghua
 * Date:     2019/4/22 22:22
 *
 * @Description: $description$
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@SpringBootTest
@RunWith(SpringRunner.class)

public class MQTest {


    /**
     * @Description: 测试发送和接收队列
     */
    @Autowired
    private SenderA queueSender;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void QueueSend() {
        int i = 2;
        for (int j = 0; j < i; j++) {
            String msg = "Queue1 msg" + j + new Date();
            try {
                queueSender.send(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void send() {
        Message message = MessageBuilder.withBody("body content".getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .setMessageId("1")
                .setHeader("header", "header")
                .build();
        amqpTemplate.send("Queue1", message);
        amqpTemplate.convertAndSend("Queue1", "body content");

    }


}