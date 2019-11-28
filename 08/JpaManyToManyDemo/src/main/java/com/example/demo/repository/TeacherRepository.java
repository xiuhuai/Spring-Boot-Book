package com.example.demo.repository;

import com.example.demo.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author longzhonghua
 * @data 2019/01/28 17:41
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findTeacherById(long id);
    void deleteById(long id);
}

