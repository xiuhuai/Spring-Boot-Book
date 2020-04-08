package com.example.demo.controller.sysuser;

import com.example.demo.entity.sysuser.SysPermission;
import com.example.demo.entity.sysuser.SysRole;
import com.example.demo.repository.SysUser.SysPermissionRepository;
import com.example.demo.repository.SysUser.SysRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longzhonghua
 * @data 2/27/2019 8:05 AM
 */
@Controller
@RequestMapping("/admin/permission")
public class SysPermissionControler {
    @Autowired
    private SysPermissionRepository sysPermissionRepository;
    @Autowired
    private SysRoleRepository sysRoleRepository;

    @RequestMapping("/add")
    public String addPermission(Model model) {
        List<SysRole> sysRole = sysRoleRepository.findAll();
        model.addAttribute("sysRole", sysRole);
        return "admin/permission/add";
    }

    @PostMapping("/add")
    public String addPermission(SysPermission sysPermission, String role) {
        List<SysRole> roles = new ArrayList<>();
        SysRole role1 = sysRoleRepository.findByRole(role);
        roles.add(role1);
        sysPermission.setRoles(roles);
        sysPermissionRepository.save(sysPermission);
        return "redirect:/admin/";
    }
}
