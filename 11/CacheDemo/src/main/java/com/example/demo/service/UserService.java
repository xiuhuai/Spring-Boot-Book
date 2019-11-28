package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

/**
 * @author longzhonghua
 * @data 2019/01/28 17:47
 */

public interface UserService {
    public User findUserById(long id);
    public User insertUser(User user);
    public User updateUserById(User user);
    public void deleteUserById(long id);
}
