package com.example.demo.model;
import lombok.Data;
/**
 * Author:   longzhonghua
 * Date:     3/22/2019 10:42 AM
 */
@Data
public class User {
    //定义id
    private long id;
    //定义用户名
    private String name;
    //定义用户年龄
    private  int age;
}
