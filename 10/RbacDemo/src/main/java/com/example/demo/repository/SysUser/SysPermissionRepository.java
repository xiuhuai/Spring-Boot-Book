package com.example.demo.repository.SysUser;

import com.example.demo.entity.sysuser.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author longzhonghua
 * @data 2019/01/27 08:26
 */

public interface SysPermissionRepository extends JpaRepository<SysPermission, Long> {
    SysPermission findById(long id);


}

