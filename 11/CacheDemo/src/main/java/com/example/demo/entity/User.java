package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author longzhonghua
 * @createdata 3/12/2019 4:35 PM
 * @description
 */

@Entity
@Table(name = "user_cache_demo")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String password;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
