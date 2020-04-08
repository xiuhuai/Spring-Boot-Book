package com.example.demo.repository.SysUser;

import com.example.demo.entity.sysuser.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */
public interface SysRoleRepository extends JpaRepository<SysRole,Long> {
	SysRole findByRole(String name);
}
