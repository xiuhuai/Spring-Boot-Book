package com.example.demo.config.authenticationhandler.jwt;


import com.example.demo.util.IpUtils;
import com.example.demo.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

/**
 * @author longzhonghua
 * @data 2/26/2019 6:48 PM
 */

@Component("jwtAuthenticationSuccessHandler")
public class JwtAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    //用户名和密码正确执行
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal != null && principal instanceof UserDetails) {
            UserDetails user = (UserDetails) principal;
            httpServletRequest.getSession().setAttribute("userDetail", user);
           String role = "";
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            for (GrantedAuthority authority : authorities){
                role = authority.getAuthority();
            }


            String token = JwtTokenUtils.createToken(user.getUsername(), role, true);
            System.out.println("role"+role);
//        String token = JwtTokenUtils.createToken(jwtUser.getUsername(), false);
            // 返回创建成功的token
            // 但是这里创建的token只是单纯的token
            // 按照jwt的规定，最后请求的时候应该是 `Bearer token`

            httpServletResponse.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token);
            httpServletResponse.setContentType("application/json;charset=utf-8");
            PrintWriter out = httpServletResponse.getWriter();
            out.write("{\"status\":\"ok\",\"message\":\"登录成功\"}");
            out.flush();
            out.close();
        }
    }

}
