package com.example.demo.service;

import com.example.demo.entity.School;

import java.util.List;

/**
 * @author longzhonghua
 * @data 2019/01/28 17:44
 */
public interface SchoolService {
    public List<School> getSchoolList();
    public School findSchoolById(long id);

}
