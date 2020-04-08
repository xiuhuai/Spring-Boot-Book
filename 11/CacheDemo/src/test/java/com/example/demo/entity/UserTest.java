package com.example.demo.entity;

import com.example.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: UserTest
 * Author:   longzhonghua
 * Date:     4/9/2019 6:02 PM
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {
    @Autowired
    private UserService userService;

    @Test
    public void insertUser() throws Exception{
        User  user= new User();
        user.setUsername("zhonghua");
        userService.insertUser(user);
    }
/*
查找用户
*/
    @Test
    public void findUserById() throws Exception{
        long id=38;
        User user = userService.findUserById(id);
        System.out.println(user.getId()+user.getUsername());
    }

}