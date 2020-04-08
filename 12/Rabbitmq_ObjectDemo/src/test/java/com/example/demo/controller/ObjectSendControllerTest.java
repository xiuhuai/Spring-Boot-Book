package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.mq.ObjectSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: ObjectSendControllerTest
 * Author:   longzhonghua
 * Date:     2019/5/9 9:03
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ObjectSendControllerTest {
    @Autowired
    private ObjectSender objectSender;

    @Test
    public void sendOjectController() {
        try {
            User user = new User();
            user.setName("longzhiran");
            user.setAge("18");
            objectSender.send(user);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}