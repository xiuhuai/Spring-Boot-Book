package com.example.demo.service;

import com.example.demo.entity.User;
import   org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.hamcrest.CoreMatchers.*;
/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: UserServiceTest
 * Author:   longzhonghua
 * Date:     3/20/2019 9:37 PM
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
//表明要在测试环境运行，底层使用的junit测试工具
@RunWith(SpringRunner.class)
// SpringJUnit支持，由此引入Spring-Test框架支持！

//启动整个spring的工程
@SpringBootTest
public class UserServiceTest {
@Autowired
private UserService userService;
    @Test
    public void getUserInfo() {
        User user = userService.getUserInfo();
        //比较实际的值和用户预期的值是否一样
        Assert.assertEquals(18,user.getAge());
        Assert.assertThat(user.getName(),is("zhonghualong"));

    }
}