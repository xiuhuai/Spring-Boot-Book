package com.example.demo.entity;

import lombok.Data;

/**
 * @author longzhonghua
 * @data 2/19/2019 8:24 PM
 */
//User（不同于JPA的点，不需要映射.JPA需要注解@entity）
@Data
public class User {
    private int id;
    private String name;
    private int age;
}