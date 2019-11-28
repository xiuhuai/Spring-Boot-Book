package com.example.demo.controller;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: UserController
 * Author:   longzhonghua
 * Date:     2019/4/11 8:58
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/addUser")
    public void addUser() {
        userService.addUser();
    }

    @PutMapping("/updateUser")
    public void updateUser() {
        userService.updateUser();
    }

    @DeleteMapping("/delete")

    public void delete() {
        userService.deleteUser();

    }
}
