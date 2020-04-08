package com.example.demo.repository.SysUser;



import com.example.demo.entity.sysuser.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */
public interface SysUserRepository extends JpaRepository<SysUser,Long> {
	SysUser findByName(String name);
	SysUser findById(long id);
}
