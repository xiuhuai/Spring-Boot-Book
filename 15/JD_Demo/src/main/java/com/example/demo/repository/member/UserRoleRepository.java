package com.example.demo.repository.member;


import com.example.demo.entity.member.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
	UserRole findByRolename(String name);
}
