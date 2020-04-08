package com.example.demo.dao;

import com.example.demo.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author longzhonghua
 * @data 2/23/2019 1:53 PM
 */
public interface SysRoleDao extends JpaRepository<SysRole,Long> {
public SysRole findByRole(String role);
        SysRole findById(long id);
}
