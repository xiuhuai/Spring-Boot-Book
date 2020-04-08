package com.example.demo.controller.bankend;

import com.example.demo.entity.member.User;
import com.example.demo.entity.member.UserRole;
import com.example.demo.repository.member.UserRepository;
import com.example.demo.repository.member.UserRoleRepository;
import com.example.demo.util.result.ExceptionMsg;
import com.example.demo.util.result.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author longzhonghua
 * @createdata 3/6/2019 11:40 AM
 * @description
 */
@Controller
@RequestMapping("admin/member")
public class MemberController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @RequestMapping("/add")
    public String toAddUser(Model model) {
        List<UserRole> userrole = userRoleRepository.findAll();
        model.addAttribute("memberrole", userrole);
        return "admin/member/add";
    }

    @PostMapping("/add")
    public ResponseData addUser(String name, String password, String role, HttpServletResponse response) {
//    public String addUser(String name,String password,String role) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(password);
        //String encodePassword = MD5Util.encode(password);//之前用的md5
        User user = new User(name, encodePassword);
        List<UserRole> roles = new ArrayList<>();
        UserRole role1 = userRoleRepository.findByRolename(role);
        roles.add(role1);
        user.setRoles(roles);
        userRepository.save(user);
        return new ResponseData(ExceptionMsg.SUCCESS);
//        return "redirect:/home/login";
    }

    @RequestMapping("/role/add")
    public String toAddMemberRole() {
        return "admin/member/role/add";
    }

    @PostMapping("/role/add")
    public String saveMemberRole(UserRole model) {
        userRoleRepository.save(model);
        return "redirect:/admin/member/role/add";
    }


    @GetMapping("")

    public ModelAndView userlist(@RequestParam(value = "start", defaultValue = "0") Integer start,
                                 @RequestParam(value = "limit", defaultValue = "5") Integer limit) {
        start = start < 0 ? 0 : start;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
       // Pageable pageable = new PageRequest(start, limit, sort);
        Pageable pageable = PageRequest.of(start, limit, sort);
        Page<User> page = userRepository.findAll(pageable);
        ModelAndView mav = new ModelAndView("admin/member/list");
        mav.addObject("page", page);
        return mav;
    }

}
