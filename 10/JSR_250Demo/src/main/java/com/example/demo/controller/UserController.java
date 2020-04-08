package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: UserController
 * Author:   longzhonghua
 * Date:     2019/5/7 10:45
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/addUser")
    public void addUser() {
        userService.addUser();
    }

    @GetMapping("/updateUser")
    public void updateUser() {
        userService.updateUser();
    }

    @GetMapping("/delete")
    public void delete() {
        userService.deleteUser();

    }
}
