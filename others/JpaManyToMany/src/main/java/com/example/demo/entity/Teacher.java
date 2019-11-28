package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author longzhonghua
 * @data 2019/01/28 17:35
 */
@Data
@Entity
@Table(name = "teachermany")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToMany(mappedBy="teachers")
    //就配置一个mappedBy,其余的交给对方配置。
    private Set<Student> students = new HashSet<Student>(0);

    public Set<Student> getStudents() {
        return students;
    }
    public void setStudents(Set<Student> students) {
        this.students = students;
    }

}
