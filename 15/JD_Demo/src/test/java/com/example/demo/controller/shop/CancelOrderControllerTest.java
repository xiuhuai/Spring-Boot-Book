package com.example.demo.controller.shop;

import com.example.demo.module.mq.CancelOrderSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;

import static org.junit.Assert.*;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: CancelOrderControllerTest
 * Author:   longzhonghua
 * Date:     2019/5/11 13:52
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CancelOrderControllerTest {


    @Autowired
    private CancelOrderSender cancelOrderSender;
    int orderId = 1;

    @Test
    public void send() {
        cancelOrderSender.sendMsg("delay_queue_1", orderId);
    }
}