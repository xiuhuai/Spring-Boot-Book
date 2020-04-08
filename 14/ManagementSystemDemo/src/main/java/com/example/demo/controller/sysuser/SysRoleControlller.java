package com.example.demo.controller.sysuser;

import com.example.demo.entity.sysuser.SysRole;
import com.example.demo.repository.SysUser.SysRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;

/**
 * @author longzhonghua
 * @data 2/26/2019 6:21 AM
 */
@Controller
@RequestMapping("admin")
public class SysRoleControlller {
    @Autowired
    private SysRoleRepository sysRoleRepository;

    @RequestMapping("/role/add")
// @PreAuthorize("hasRole('ROLE_CESHI')")//基于角色的可以
 //   @PreAuthorize("hasPermission('rbac')")

    public String addRole() {
        return "admin/role/add";
    }

    @RequestMapping("/role")

    public String addRole(SysRole model) {
        String role = "ROLE_" + model.getRole();
        model.setRole(role);
        sysRoleRepository.save(model);
        return "redirect:/admin/";
    }

}
