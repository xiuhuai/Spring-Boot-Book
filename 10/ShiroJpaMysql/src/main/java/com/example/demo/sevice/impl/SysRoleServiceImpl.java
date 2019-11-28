package com.example.demo.sevice.impl;
import com.example.demo.dao.SysRoleDao;
import com.example.demo.entity.SysRole;
import com.example.demo.sevice.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author longzhonghua
 * @data 2/23/2019 1:52 PM
 */
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleDao sysRoleDao;
    @Override
    public SysRole findByRole(String role) {
        return sysRoleDao.findByRole(role);
    }
}
