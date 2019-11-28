package com.example.demo.service.impl;


import com.example.demo.service.MethodSecurityService;
import org.springframework.stereotype.Service;

@Service
public class MethodSecurityServiceImpl implements MethodSecurityService {
  @Override
  public String requiresUserRole() {
    return "You have ROLE USER";
  }
}