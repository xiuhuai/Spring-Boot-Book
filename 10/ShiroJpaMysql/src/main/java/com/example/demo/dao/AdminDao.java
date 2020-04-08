package com.example.demo.dao;


import com.example.demo.entity.Admin;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminDao extends JpaRepository<Admin,Long> {
    public Admin findByUsername(String username);
    Admin findById(long id);
}