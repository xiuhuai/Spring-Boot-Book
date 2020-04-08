package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author longzhonghua
 * @data 2/19/2019 8:25 PM
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/querybyid")
    User queryById(int id) {
        return userMapper.queryById(id);
    }

    @RequestMapping("/")
    List<User> queryAll() {
        return userMapper.queryAll();
    }


    @RequestMapping("/add")
    String add(User user) {
        return userMapper.add(user) == 1 ? "success" : "failed";
    }

    @RequestMapping("/updatebyid")
    String updateById(User user) {
        return userMapper.updateById(user) == 1 ? "success" : "failed";
    }

    @RequestMapping("/delbyid")
    String delById(int id) {
        return userMapper.delById(id) == 1 ? "success" : "failed";
    }
}