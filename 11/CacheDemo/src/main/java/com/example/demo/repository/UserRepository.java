package com.example.demo.repository;


import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author longzhonghua
 * @data 2019/01/28 17:41
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(long id);

}

