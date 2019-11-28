package com.example.demo.service;


import com.example.demo.entity.Student;

import java.util.List;

/**
 * @author longzhonghua
 * @data 2019/01/27 21:56
 */
public interface StudentService {
    public List<Student> getStudentlist();
    public Student findStudentById(long id);
}
