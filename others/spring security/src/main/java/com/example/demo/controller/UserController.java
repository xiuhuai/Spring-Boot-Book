package com.example.demo.controller;

import javax.servlet.http.HttpServletResponse;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 

@Controller
public class UserController {
	
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	  
	    @RequestMapping(value = "/addUser", method = RequestMethod.GET)

	    public void addUser(@RequestParam(value="name") String name,@RequestParam(value="password")  String password,@RequestParam(value="role")   String role, HttpServletResponse response) {
System.out.println(name+password+role);
	    }
	
	@PreAuthorize("hasPermission('user', 'ROLE_USER')")
	@RequestMapping(value = "/addUser2", method = RequestMethod.GET)
    public void addUser2(@RequestParam(value="name") String name,@RequestParam(value="password")  String password,@RequestParam(value="role")   String role, HttpServletResponse response) {
System.out.println(name+password+role);
    }
    @Autowired
    private UserService userService;
    @ResponseBody
    @RequestMapping(value = "/addUser3", method = RequestMethod.GET)
    public void requiresRole() {
        userService.addUser();

    }
    @ResponseBody
    @RequestMapping(value = "/updateUser", method = RequestMethod.GET)
    public void updateUser() {
        userService.updateUser();

    }
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete() {
        userService.delete();

    }
	}
