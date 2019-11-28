package com.example.demo.service.impl;

import com.example.demo.entity.School;
import com.example.demo.repository.SchoolRepository;
import com.example.demo.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author longzhonghua
 * @data 2019/01/28 17:48
 */
public class SchoolServiceImpl  implements SchoolService {
@Autowired
private SchoolRepository schoolRepository;
    @Override
    public List<School> getSchoolList() {
        return schoolRepository.findAll();
    }

    @Override
    public School findSchoolById(long id) {
        return schoolRepository.findSchoolById(id);
    }


}
