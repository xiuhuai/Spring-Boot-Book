package com.example.demo.Service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author longzhonghua
 * @data 2/26/2019 9:31 AM
 */
public interface RbacService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}