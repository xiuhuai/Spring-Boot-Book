package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author longzhonghua
 * @data 2019/01/27 20:44
 */
@Entity
@Data
@Table(name = "studentmany")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(columnDefinition = "enum('male','female')")
    private String sex;//类型
    @ManyToMany
    @JoinTable(name = "teacher_student",    //用来指定中间表的名称
//用于指定本表在中间表的字段名称，以及中间表依赖的是本表的哪个字段
            joinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "id")},
            //用于指定对方表在中间表的字段名称，以及中间表依赖的是它的哪个字段
            inverseJoinColumns = {@JoinColumn(name = "teacher_id", referencedColumnName = "id")}
    )
    private Set<Teacher> teachers = new HashSet<Teacher>(0);

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

}
