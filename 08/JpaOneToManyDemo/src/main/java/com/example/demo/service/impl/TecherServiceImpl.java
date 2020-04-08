package com.example.demo.service.impl;

import com.example.demo.entity.Teacher;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author longzhonghua
 * @data 2019/01/28 17:51
 */
public class TecherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Override
    public List<Teacher> getTeacherList() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findTeacherById(long id) {
        return teacherRepository.findTeacherById(id);
    }
}
