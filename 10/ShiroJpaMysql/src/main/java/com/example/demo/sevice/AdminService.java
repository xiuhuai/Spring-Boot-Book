package com.example.demo.sevice;
import com.example.demo.entity.Admin;
public interface AdminService {
    public Admin findByUsername(String username);
}