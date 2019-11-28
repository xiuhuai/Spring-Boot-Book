package com.example.demo.service.impl;

import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;

/**
 * @author longzhonghua
 * @data 2019/01/24 13:20
 */
@Service
@RolesAllowed("ROLE_ADMIN")
public class UserServiceImpl implements UserService {
    @Override
    public String addUser() {
        System.out.println("addUser" );
        return null;
    }
    @RolesAllowed("ROLE_USER")
    @Override
    public String updateUser() {
        System.out.println("updateUser" );
        return null;
    }

    @Override
    @RolesAllowed({"ROLE_USER","ROLE_ADMIN"})
    public String delete() {
        System.out.println("delete" );
        return null;
    }
}
