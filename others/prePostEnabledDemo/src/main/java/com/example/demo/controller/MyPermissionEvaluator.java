package com.example.demo.controller;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: MyPermissionEvaluator
 * Author:   longzhonghua
 * Date:     2019/4/11 10:47
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class MyPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1) {
        if ("ROLE".equals("ROLE_ADMIN")) {
            return this.hasPermission(authentication, o, o1);
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return true;
    }
}