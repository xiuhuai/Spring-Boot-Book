package com.example.demo.controller.member;
import com.example.demo.controller.BaseController;
import com.example.demo.entity.member.User;
import com.example.demo.entity.member.UserRole;
import com.example.demo.entity.sysuser.SysUser;
import com.example.demo.repository.SysUser.SysUserRepository;
import com.example.demo.repository.member.UserRepository;
import com.example.demo.repository.member.UserRoleRepository;
import com.example.demo.util.AsyncSendEmailService;
import com.example.demo.util.DateUtils;
import com.example.demo.util.MD5Util;
import com.example.demo.util.result.ExceptionMsg;
import com.example.demo.util.result.Response;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("home")
public class UserController extends BaseController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SysUserRepository adminUserRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Resource
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String mailFrom;
    @Value("${mail.subject.forgotpassword}")
    private String mailSubject;
    @Value("${mail.subject.active}")
    private String mailActiveSubject;
    @Value("${mail.content.forgotpassword}")
    private String mailContent;
    @Value("${mail.content.active}")
    private String mailActiveContent;
    @Value("${forgotpassword.url}")
    private String forgotpasswordUrl;
    @Value("${activeuser.url}")
    private String activeuserUrl;


    @RequestMapping("/")
    public String index() {

        return "/user/index";
    }


    @RequestMapping("/register/mobile")
    public String regist(Model model) {
      /*  List<UserRole> userrole=userRoleRepository.findAll();

        model.addAttribute("userrole", userrole);*/
        return "user/registerByMobile";
    }

    @RequestMapping("/register/email")
    public String registByEmail(Model model) {
      /*  List<UserRole> userrole=userRoleRepository.findAll();

        model.addAttribute("userrole", userrole);*/
        return "user/registerByEmail";
    }

    @ResponseBody
    @RequestMapping(value = "/register/mobile", method = RequestMethod.POST)
    public Response regist(User user) {
        try {

            User userNameUser = userRepository.findByName(user.getName());
            SysUser admingusername = adminUserRepository.findByName(user.getName());
            if (null != userNameUser || null != admingusername) {
                return result(ExceptionMsg.UserNameUsed);
            }
            User userMobile = userRepository.findByMobile(user.getMobile());
            if (null != userMobile) {
                return result(ExceptionMsg.MobileUsed);
             }

            // String encodePassword = MD5Util.encode(password);
            BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();

            user.setPassword(encoder.encode(user.getPassword()));
            user.setCreateTime(DateUtils.getCurrentTime());
            user.setLastModifyTime(DateUtils.getCurrentTime());
            user.setProfilePicture("img/favicon.png");
            List<UserRole> roles = new ArrayList<>();
            UserRole role1 = userRoleRepository.findByRolename("ROLE_USER");
            roles.add(role1);
            user.setRoles(roles);
            userRepository.save(user);


            // 添加默认收藏夹
			/*Favorites favorites = favoritesService.saveFavorites(user.getId(), "未读列表");
			// 添加默认属性设置
			configService.saveConfig(user.getId(),String.valueOf(favorites.getId()));	*/

        } catch (Exception e) {

            //logger.error("create user failed, ", e);
            return result(ExceptionMsg.FAILED);
        }
        return result();
    }

    @Autowired
    AsyncSendEmailService asyncSendEmailService;
    @Autowired
    private AmqpTemplate rabbitTemplate;
    @ResponseBody
    @RequestMapping(value = "/register/email", method = RequestMethod.POST)
    public Response registByEmail(User user) {
        try {
            User registUser = userRepository.findByEmail(user.getEmail());
            if (null != registUser) {
                return result(ExceptionMsg.EmailUsed);
            }
            User userNameUser = userRepository.findByName(user.getName());
            SysUser admingusername = adminUserRepository.findByName(user.getName());
            if (null != userNameUser || null != admingusername) {
                return result(ExceptionMsg.UserNameUsed);
            }

            BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();

            user.setPassword(encoder.encode(user.getPassword()));
            // String encodePassword = MD5Util.encode(password);

           // user.setPassword(MD5Util.encode(user.getPassword()));
            user.setCreateTime(DateUtils.getCurrentTime());
            user.setLastModifyTime(DateUtils.getCurrentTime());
            user.setProfilePicture("img/favicon.png");
            List<UserRole> roles = new ArrayList<>();
            UserRole role1 = userRoleRepository.findByRolename("ROLE_USER");
            roles.add(role1);
            user.setRoles(roles);

            userRepository.save(user);
            rabbitTemplate.convertAndSend("reg_email", user.getEmail());
            asyncSendEmailService.sendVerifyemail(user.getEmail());
            //send email 已经异步执行,下面代码注释掉
        } catch (Exception e) {

            //logger.error("create user failed, ", e);
            return result(ExceptionMsg.FAILED);
        }
        return result();
    }


    @RequestMapping("/showactive")
    public String showactive(Model model, @RequestParam(value = "email", required = false, defaultValue = "") String email) {
        model.addAttribute("email", email);

        return "user/showemailactive";
    }


    @RequestMapping(value = "/newPassword", method = RequestMethod.GET)
    public String newPassword() {
        return "user/newpassword";
    }
   /* @RequestMapping(value="/activeuserEmail",method=RequestMethod.GET)
    public String activeuserEmailget(String email,String sid) {
        return "user/activeuserEmail";
    }*/

    @RequestMapping(value = "/activeuserEmail", method = RequestMethod.GET)
    public String activeuserEmail(Model model, String email, String sid) {
        try {
            User user = userRepository.findByEmail(email);
            Timestamp outDate = Timestamp.valueOf(user.getOutDate());
            if (outDate.getTime() <= System.currentTimeMillis()) { //表示已经过期
                model.addAttribute("states", "签名过期");
                return "user/activeuserEmail";
//                System.out.print("过期");
            }
            String key = user.getEmail() + "$" + outDate.getTime() / 1000 * 1000 + "$" + user.getValidataCode();//数字签名
            String digitalSignature = MD5Util.encode(key);
            if (digitalSignature.equals(sid)) {
                //return result(ExceptionMsg.LinkOutdated);
                userRepository.setActive(1, user.getEmail());


            }
            if (!digitalSignature.equals(sid)) {
                model.addAttribute("states", "签名不对");
                return "user/activeuserEmail";

            }


//            userRepository.
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("failed, ", e);
//            return result(ExceptionMsg.FAILED);
        }
        model.addAttribute("states", "激活成功");
        return "user/activeuserEmail";

    }


    @GetMapping("/user")

    public ModelAndView userlist(@RequestParam(value = "start", defaultValue = "0") Integer start,
                                 @RequestParam(value = "limit", defaultValue = "5") Integer limit) {
        start = start < 0 ? 0 : start;
// Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "categoryid","desc");
      Sort sort = new Sort(Sort.Direction.DESC, "id");
// Pageable pageable = new PageRequest(start, limit, sort);
        Pageable pageable = new PageRequest(start, limit, sort);

        Page<User> page = userRepository.findAll(pageable);


        ModelAndView mav = new ModelAndView("user/userlist");
        mav.addObject("page", page);
        return mav;
    }


}
