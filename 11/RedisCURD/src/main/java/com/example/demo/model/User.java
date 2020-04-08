package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author longzhonghua
 * @data 2/21/2019 3:30 PM
 */
@Data
public class User  implements Serializable {
     private String id;
     private String name;
     private int age;

}
