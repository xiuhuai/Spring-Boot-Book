package com.example.demo.entity;

import com.example.demo.repository.SchoolRepository;
import com.example.demo.repository.TeacherRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: OneToManyTest
 * Author:   longzhonghua
 * Date:     2019/4/27 23:33
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OneToManyTest {

    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private TeacherRepository teacherRepository;


    @Test
    public void add() {
        School school1 = new School();
        school1.setName("清华大学");
        schoolRepository.save(school1);
        Teacher teacher = new Teacher();
        teacher.setName("long");
        teacher.setSchool(school1);
        teacherRepository.save(teacher);
    }

    @Test
    public void find() {
        School school1 = new School();
        school1 = schoolRepository.findSchoolById(3);
        List<Teacher> teacherList = school1.getTeacherList();
        System.out.println(school1.getName());
        for (Teacher teacher : teacherList) {
            System.out.println(teacher.getName());
        }
    }

    @Test
    public void deleteSchoolById() {
        schoolRepository.deleteById(3);
    }

    @Test
    public void deleteTeacherById() {
        teacherRepository.deleteById(7);
    }
}