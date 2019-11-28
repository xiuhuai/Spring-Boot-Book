package com.example.demo.Service.SysUser;

import com.example.demo.entity.sysuser.SysUser;
import org.springframework.data.domain.Page;

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */

public interface SysUserService {
    void save(SysUser adminUser);//保存用户
    Page<SysUser> PageByAdminUser(Integer page, Integer size);//对用户数据进行分页
    //public SysUser findByUserName(String username);

}
