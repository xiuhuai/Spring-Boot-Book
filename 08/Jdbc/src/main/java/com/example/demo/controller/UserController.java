package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author longzhonghua
 * @data 2/24/2019 9:51 AM
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //创建数据表
    @GetMapping("createUserTable")
    public String createUserTable() throws Exception {
        String sql = "CREATE TABLE `user` (\n" +
                "  `id` int(10) NOT NULL AUTO_INCREMENT,\n" +
                "  `username` varchar(100) DEFAULT NULL,\n" +
                "  `password` varchar(100) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;\n" +
                "\n";

            jdbcTemplate.execute(sql);
            return "创建表成功";

    }

    //saveUserTest
    //添加一个测试数据
    @GetMapping("saveUserTest")
    public String saveUserTest()throws Exception  {
        String sql = "INSERT INTO user (USERNAME,PASSWORD) VALUES ('longzhiran','123456')";
        int rows = jdbcTemplate.update(sql);
        return "执行成功，影响" + rows + "行";
    }

    //batchSaveUser
    //批量添加测试数据
    @GetMapping("batchSave")
    public String batchSaveUser()throws Exception  {
        String sql =
                "INSERT INTO user (USERNAME,PASSWORD) VALUES (?,?)";
        List<Object[]> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String[] arr = new String[2];
            arr[0] = "longzhiran" + i;
            arr[1] = "123456" + i;
            arrayList.add(arr);
        }
        jdbcTemplate.batchUpdate(sql, arrayList);
        return "执行成功";
    }

    //addUser?userName=longzhiran&passWord=123456
    //
    @GetMapping("add")
    public String addUser(String userName, String passWord) throws Exception {
        String sql = "INSERT INTO user (USERNAME,PASSWORD) VALUES (?,?)";
        int rows = jdbcTemplate.update(sql, userName, passWord);
        return "执行成功，影响" + rows + "行";
    }

    //updateUserPassword?id=1&passWord=12345678
    @GetMapping("updateUserPassword")
    public String updateUserPassword(int id, String passWord) throws Exception {
        String sql = "UPDATE user SET PASSWORD = ? WHERE ID = ?";
        int rows = jdbcTemplate.update(sql, passWord, id);
        return "执行成功，影响" + rows + "行";
    }

    //deleteUserById?id=1
    @GetMapping("deleteUserById")
    public String deleteUserById(int id) throws Exception {
        String sql = "DELETE FROM  user  WHERE ID = ?";
        int rows = jdbcTemplate.update(sql, id);
        return "执行成功，影响" + rows + "行";
    }


    //getUserByName?userName=longzhiran
    @GetMapping("getUserByName")
    public List getUserByName(String userName)throws Exception {
        String sql = "SELECT * FROM user WHERE USERNAME = ?";
        List<User> list = jdbcTemplate.query(sql, new User(), new Object[]{userName});
        return list;
    }

    //getMapById?id=1
    @GetMapping("getMapById")
    public Map getMapById(Integer id) throws Exception {
        String sql = "SELECT * FROM user WHERE ID = ?";
        Map map = jdbcTemplate.queryForMap(sql, id);
        return map;
    }

    //getUserById?id=1
    @GetMapping("getUserById")
    public User getUserById(Integer id) throws Exception {
        String sql = "SELECT * FROM user WHERE ID = ?";
        User user = jdbcTemplate.queryForObject(sql, new User(), new Object[]{id});
        return user;
    }

    //getAll
    @GetMapping("list")
    public List<User> list() throws Exception {
        String sql = "SELECT * FROM user";

        List<User> userList = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper(User.class));
        return userList;
    }

}