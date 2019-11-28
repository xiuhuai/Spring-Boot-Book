package com.example.demo.Service.SysUser;

import com.example.demo.entity.sysuser.SysUser;
import com.example.demo.repository.SysUser.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public void save(SysUser adminUser) {
        sysUserRepository.save(adminUser);
    }

    @Override
    public Page<SysUser> PageByAdminUser(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page,size, Sort.Direction.ASC, "id");
        return sysUserRepository.findAll(pageable);
    }

   /* @Override
    public SysUser findByUserName(String username) {
        return sysUserRepository.findByName(username);
    }*/
}
