package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author longzhonghua
 * @data 2019/01/28 17:54
 */
@RestController
@RequestMapping("manytomany")
public class ManyToManyController {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;



    @RequestMapping("onetomanylist")
    public void setsStudentRepository() {
        Student stu1 = new Student();
        stu1.setName("xuesheng1");
        Teacher tea1 = new Teacher();
        tea1.setName("laoshi1");

  //  teacherRepository.save(tea1);

        studentRepository.save(stu1);
    }

}
