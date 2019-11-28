package com.example.demo.repository.sys;


import com.example.demo.entity.sys.SysUserLoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserLoginLogRepository extends JpaRepository<SysUserLoginLog,Long> {
    SysUserLoginLog findById(long id);


}
