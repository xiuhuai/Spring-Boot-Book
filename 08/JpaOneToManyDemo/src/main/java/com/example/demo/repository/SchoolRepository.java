package com.example.demo.repository;

import com.example.demo.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author longzhonghua
 * @data 2019/01/28 17:38
 */
public interface SchoolRepository extends JpaRepository<School, Long> {
    School findSchoolById(long id);
    void deleteById(long id);
}
