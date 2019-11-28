package com.example.demo.service.Impl;

import com.example.demo.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: UserServiceImpl
 * Author:   longzhonghua
 * Date:     2019/4/11 8:56
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Service

public class UserServiceImpl implements UserService {
    @Override
    public String addUser() {
        System.out.println("addUser");
        return null;
    }

    @Override
    @RolesAllowed({"ROLE_USER","ROLE_ADMIN"})
    public String updateUser() {
        System.out.println("updateUser");
        return null;
    }

    @Override
    @PreAuthorize("hasPermission(#baseReq,'read')")
    public String deleteUser() {
        System.out.println("delete");
        return null;
    }
}
