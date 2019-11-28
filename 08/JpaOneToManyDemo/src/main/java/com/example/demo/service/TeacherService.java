package com.example.demo.service;

import com.example.demo.entity.Teacher;

import java.util.List;

/**
 * @author longzhonghua
 * @data 2019/01/28 17:47
 */
public interface TeacherService {
    public List<Teacher> getTeacherList();
    public Teacher findTeacherById(long id);
}
