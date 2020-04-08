package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @author longzhonghua
 * @data 2019/01/27 20:44
 */
@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(columnDefinition = "enum('male','female')")
    private String sex;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="teacher_student",joinColumns={@JoinColumn(name="s_id")},inverseJoinColumns={@JoinColumn(name="t_id")})
    private Set<Teacher> teachers;
}

