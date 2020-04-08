package com.example.demo.config;

import com.example.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: IocTest
 * Author:   longzhonghua
 * Date:     2019/4/16 17:13
 *
 * @Description: $description$
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@RunWith(SpringRunner.class)
@SpringBootTest

public class IocTest {
    @Autowired
    private ApplicationContext applicationContext;
    @Test
    public void testIoc() {
        User user = (User) applicationContext.getBean("user1");
        System.out.println(user);
    }

}