package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: UserService
 * Author:   longzhonghua
 * Date:     3/20/2019 9:34 PM
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Service
public class UserService {

    public User getUserInfo(){
        User user = new User();
        user.setName("zhonghua");
        user.setAge(18);
        return user;
    }
}
