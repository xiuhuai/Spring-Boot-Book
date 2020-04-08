package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author longzhonghua
 * @data 2019/01/27 21:28
 */
@Entity
@Data
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
//    @OneToMany(cascade = CascadeType.ALL)
@OneToMany()
    @JoinColumn(name = "school_id")
    private List<Teacher> teacherList;
}
