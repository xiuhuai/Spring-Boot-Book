package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: CoExampleTest
 * Author:   longzhonghua
 * Date:     2019/4/28 14:59
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CoExampleTest {
    @Autowired
    private CoExample coExample;


    @Test
    public void  getName() {
        System.out.println(coExample.getName());
    }

    @Test
    public void get_age() {
        System.out.println(coExample.getAge());
    }

    @Test
    public void getAddress() {
        System.out.println(coExample.getAddress());
    }


}