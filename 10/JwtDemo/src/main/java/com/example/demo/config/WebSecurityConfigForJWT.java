package com.example.demo.config;

import com.example.demo.module.filter.JWTAuthorizationFilter;

import com.example.demo.service.jwt.JwtUserSecurityService;
import com.example.demo.service.member.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */
@Configuration//指定为配置类
@EnableWebSecurity//指定为Spring Security配置类
@EnableGlobalMethodSecurity(prePostEnabled = true) // 启用方法安全设置
//@EnableGlobalAuthentication

    public class WebSecurityConfigForJWT extends WebSecurityConfigurerAdapter {
        @Autowired
        private AuthenticationSuccessHandler jwtAuthenticationSuccessHandler;
        @Autowired
        private AuthenticationFailureHandler jwtAuthenticationFailHander;

        //装载BCrypt密码编码器
        @Bean
        public PasswordEncoder passwordEncoder3() {
            return new BCryptPasswordEncoder();   // 使用 BCrypt 加密
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/jwt/**").
                    //指定登录认证的Controller
                            formLogin().usernameParameter("name").passwordParameter("password").loginPage("/jwt/login").successHandler(
                    jwtAuthenticationSuccessHandler).failureHandler(jwtAuthenticationFailHander)
                    .and()
                    .authorizeRequests()
                    //登录相关
                    .antMatchers("/register/mobile").permitAll()
                    .antMatchers("/article/**").authenticated()
                   .antMatchers("/tasks/**").hasRole("USER")
                    //.antMatchers(HttpMethod.POST, "/jwt/tasks/**").hasRole("USER")
                    .and()//.addFilter(new JWTAuthenticationFilter(authenticationManager()))
                    .addFilter(new JWTAuthorizationFilter(authenticationManager()));


            http.logout().permitAll();

            http.cors().and().csrf().ignoringAntMatchers("/jwt/**");

        }

        @Bean
        UserDetailsService JwtUserSecurityService() {
            return new JwtUserSecurityService();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(JwtUserSecurityService()).passwordEncoder(new BCryptPasswordEncoder() {
            });
        }
    }



