package com.example.demo.repository;


import com.example.demo.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author longzhonghua
 * @data 2019/01/27 22:04
 */
public interface CardRepository extends JpaRepository<Card,Long> {
    Card findById(long id);
}
