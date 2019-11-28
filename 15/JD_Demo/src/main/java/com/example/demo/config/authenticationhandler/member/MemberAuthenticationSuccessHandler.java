package com.example.demo.config.authenticationhandler.member;

import com.example.demo.entity.sys.MemberLoginLog;
import com.example.demo.repository.sys.MemberLoginLogRepository;
import com.example.demo.util.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author longzhonghua
 * @data 2/26/2019 6:48 PM
 */

@Component("MemberAuthenticationSuccessHandler")
public class MemberAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private MemberLoginLogRepository memberLoginLogRepository;

    //用户名和密码正确执行
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal != null && principal instanceof UserDetails) {


                        /*    UserDetails user = (UserDetails) principal;
                            //1、添加 Session
                            httpServletRequest.getSession().setAttribute("userDetail", user);
                            //2、写入日志
                            logger.info("【用户已登录】" + user.getUsername());
                            //3、写入数据库login_record表
                            LoginRecord loginRecord = new LoginRecord();
                            loginRecord.setLoginIp(IPUtil.getIpAddr(httpServletRequest));
                            loginRecord.setLoginTime(System.currentTimeMillis());
                            loginRecord.setUser((User) user);
                            loginRecordRepository.save(loginRecord);
                            //4、页面跳转到首页
                            httpServletResponse.sendRedirect(ctx);//即 /forum*/
            UserDetails user = (UserDetails) principal;
            //1、添加 Session
            httpServletRequest.getSession().setAttribute("userDetail", user);

            //2、写入日志
//                            logger.info("【用户已登录】" + user.getUsername());
            //3、写入数据库login_record表
            MemberLoginLog loginRecord = new MemberLoginLog();
            loginRecord.setLoginip(IpUtils.getIpAddr(httpServletRequest));
            loginRecord.setLogintime(System.currentTimeMillis());
            loginRecord.setUsername(user.getUsername());
            loginRecord.setStates(1);
            loginRecord.setWay(1);
            memberLoginLogRepository.save(loginRecord);
            //4、页面跳转到首页
            // httpServletResponse.sendRedirect(ctx);//即 /forum
       /*  jwt配置   String token = JwtTokenUtils.createToken(user.getUsername(),false);
            httpServletResponse.addHeader(JwtTokenUtils.TOKEN_HEADER,JwtTokenUtils.TOKEN_PREFIX+token);*/
            httpServletResponse.setContentType("application/json;charset=utf-8");
            PrintWriter out = httpServletResponse.getWriter();
            out.write("{\"status\":\"ok\",\"message\":\"登录成功\"}");
            out.flush();
            out.close();

        }

    }

}
