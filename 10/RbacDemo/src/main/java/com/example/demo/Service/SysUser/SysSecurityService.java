package com.example.demo.Service.SysUser;

import com.example.demo.entity.sysuser.SysUser;
import com.example.demo.repository.SysUser.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */

//@Service
public class SysSecurityService implements UserDetailsService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        SysUser user = sysUserRepository.findByName(name);
        if (user == null) {

            throw new UsernameNotFoundException("用户名不存在");

        } else if (!user.getEnabled()) { //被锁定，无法登录
            throw new LockedException("用户被锁定");
        }
        System.out.println(user.getEnabled());
        return user;
    }
}
