package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author longzhonghua
 * @data 2/21/2019 1:21 PM
 */
@Data
public class User implements Serializable {

    private String name;

    private String age;


}