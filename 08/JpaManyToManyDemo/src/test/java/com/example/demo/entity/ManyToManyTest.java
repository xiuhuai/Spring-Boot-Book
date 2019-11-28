package com.example.demo.entity;

import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: ManyToManyTest
 * Author:   longzhonghua
 * Date:     2019/4/28 7:59
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ManyToManyTest {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;


    @Test
    public void add() {

        Set<Teacher> teachers = new HashSet<>();
        Set<Student> students = new HashSet<>();

        Student student1 = new Student();
        student1.setName("zhonghua");
        students.add(student1);
        studentRepository.save(student1);

        Student student2 = new Student();
        student2.setName("zhiran");
        students.add(student2);
        studentRepository.save(student2);

        Teacher teacher1 =new Teacher();
        teacher1.setName("龙老师");
        teacher1.setStudents(students);
        teachers.add(teacher1);
        teacherRepository.save(teacher1);

    }

}