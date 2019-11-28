package com.example.demo.controller.jwtmember;

import com.example.demo.controller.BaseController;
import com.example.demo.entity.member.User;
import com.example.demo.entity.member.UserRole;
import com.example.demo.repository.member.UserRepository;
import com.example.demo.repository.member.UserRoleRepository;
import com.example.demo.util.DateUtils;
import com.example.demo.util.result.ExceptionMsg;
import com.example.demo.util.result.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longzhonghua
 * @createdata 3/13/2019 10:10 PM
 * @description
 */
@RestController
@RequestMapping("jwt")
public class JwtUserController extends BaseController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @RequestMapping(value = "/register/mobile", method = RequestMethod.POST)

    public Response regist(User user) {
        try {
            User userName = userRepository.findByName(user.getName());
            if (null != userName) {
                return result(ExceptionMsg.UserNameUsed);
            }
            User userMobile = userRepository.findByMobile(user.getMobile());
            if (null != userMobile) {
                return result(ExceptionMsg.MobileUsed);
            }

            // String encodePassword = MD5Util.encode(password);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            user.setCreateTime(DateUtils.getCurrentTime());
            user.setLastModifyTime(DateUtils.getCurrentTime());
            user.setProfilePicture("img/avater.png");
            List<UserRole> roles = new ArrayList<>();
            UserRole role1 = userRoleRepository.findByRolename("ROLE_USER");
            roles.add(role1);
            user.setRoles(roles);
            userRepository.save(user);

        } catch (Exception e) {

            return result(ExceptionMsg.FAILED);
        }
        return result();
    }
}
