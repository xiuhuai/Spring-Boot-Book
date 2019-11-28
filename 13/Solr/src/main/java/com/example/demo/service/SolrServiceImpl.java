package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author longzhonghua
 * @data 2/24/2019 11:35 PM
 */
@Service
public class SolrServiceImpl implements SolrService {
    @Override
    public List<User> addUser() {
        List<User> list = new ArrayList<>();
        User user = new User();
        for (int i = 0; i < 5; i++) {
            user.setId(UUID.randomUUID().toString().replace("-", ""));
            user.setName("龙知然" + i);
     /*       if (i % 2 == 0) {
                user.setSex("男");
            } else {
                user.setSex("女");
            }
            user.setAddress("武汉" + i);*/
            list.add(user);
        }
        return list;
    }
}
