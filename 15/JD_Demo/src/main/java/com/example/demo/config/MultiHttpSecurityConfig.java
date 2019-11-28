package com.example.demo.config;

import com.example.demo.config.authenticationhandler.member.MemberAuthenticationFailHandler;
import com.example.demo.config.authenticationhandler.member.MemberAuthenticationSuccessHandler;
import com.example.demo.module.filter.JWTAuthorizationFilter;
import com.example.demo.service.SysUser.SysSecurityService;
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
public class MultiHttpSecurityConfig {
    @Configuration
    @Order(1)

    public class WebSecurityConfigForAdmin extends WebSecurityConfigurerAdapter {
        @Autowired
        private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
        @Autowired
        private AuthenticationFailureHandler myAuthenticationFailHandler;

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();   // 使用 BCrypt 加密
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/admin/**").//这里必须要加,不然走这里了,就不进行下面的几个验证了
                    //指定登录认证的Controller
                            formLogin().usernameParameter("uname").passwordParameter("pwd").loginPage("/admin/login").successHandler(
                    myAuthenticationSuccessHandler).failureHandler(myAuthenticationFailHandler)
                    .and()
                    .authorizeRequests()
                    //登录相关
                    .antMatchers("/admin/login").permitAll()
                    //  .antMatchers("/admin/**").permitAll()//.hasRole("ADMIN")
                    //rabc相关
                    .antMatchers("/admin/**").access("hasRole('ADMIN') or @rbacService.hasPermission(request,authentication)")

                    .antMatchers("/redis/**").permitAll();

            http.logout().logoutUrl("/admin/logout").permitAll();
            http.rememberMe().rememberMeParameter("rememberme");//记住我功能
            http.csrf().ignoringAntMatchers("/admin/upload");
            http.headers().frameOptions().sameOrigin();//解决X-Frame-Options deny 造成的页面空白,不然后台不能用frame
        }

        @Bean
        UserDetailsService Service() {
            return new SysSecurityService();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(Service()).passwordEncoder(new BCryptPasswordEncoder() {
            });

        }
    }

    @Configuration
    @Order(2)
    public class WebSecurityConfigForUser extends WebSecurityConfigurerAdapter {
        @Autowired
        private MemberAuthenticationSuccessHandler MemberAuthenticationSuccessHandler;
        @Autowired
        private MemberAuthenticationFailHandler MemberAuthenticationFailHandler;


        @Bean
        public PasswordEncoder passwordEncoder2() {
            return new BCryptPasswordEncoder();   // 使用 BCrypt 加密
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
           // http.antMatcher("/home/**").
            //为了在product页面获取到用户信息,进行了url修改.2019.4.12
            http.antMatcher("/**").
                    //指定登录认证的Controller
                            formLogin().usernameParameter("uname").passwordParameter("pwd").loginPage("/home/login").successHandler(
                    MemberAuthenticationSuccessHandler).failureHandler(MemberAuthenticationFailHandler)
                    .and()
                    .authorizeRequests()
                    //登录相关
                    .antMatchers("/home/login", "/home/register/mobile", "/home/register/email").permitAll()
                    .antMatchers("/home/**").hasRole("USER")
                    //限制购物车必须登录
                    .antMatchers("/cart/","/cart").hasRole("USER");


            //rabc相关

            http.logout().logoutUrl("/home/logout").permitAll();
            http.rememberMe().rememberMeParameter("rememberme");//记住我功能
            http.headers().frameOptions().sameOrigin();//解决X-Frame-Options deny 造成的页面空白,不然后台不能用frame
        }

        @Bean
        UserDetailsService UserService() {
            return new UserSecurityService();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(UserService()).passwordEncoder(new BCryptPasswordEncoder() {
            });
        }
    }

    @Configuration
    @Order(3)
    public class WebSecurityConfig3 extends WebSecurityConfigurerAdapter {
        @Autowired
        private AuthenticationSuccessHandler jwtAuthenticationSuccessHandler;
        @Autowired
        private AuthenticationFailureHandler jwtAuthenticationFailHandler;

        //装载BCrypt密码编码器
        @Bean
        public PasswordEncoder passwordEncoder3() {
            return new BCryptPasswordEncoder();   // 使用 BCrypt 加密
        }


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/jwt/**").
                    //指定登录认证的Controller
                            formLogin().usernameParameter("name").passwordParameter("pwd").loginPage("/jwt/login").successHandler(
                    jwtAuthenticationSuccessHandler).failureHandler(jwtAuthenticationFailHandler)
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
            //http.rememberMe().rememberMeParameter("rememberme");//记住我功能
            //jwt配置
            // http.antMatcher("/article/**").addFilter(new JWTAuthenticationFilter(authenticationManager()));

            http.cors().and().csrf().ignoringAntMatchers("/jwt/**");


            //jwt配置

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

    @Configuration
    @Order(4)
    public class WebSecurityConfig4 extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(WebSecurity web) throws Exception {
            // 不拦截静态资源
            web.ignoring().antMatchers("/", "/static/html/**", "/js/**", "/jquery/**", "/js/plugins/layer/**", "/css/**", "/images/**", "/img/**", "/UPLOAD/img/**", "/mqtt/send");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/")
                    .authorizeRequests()//启用基于 HttpServletRequest 的访问限制，开始配置哪些URL需要被保护、哪些不需要被保护
                    //设置静态资源均可以访问
                    .antMatchers("/css/**", "/js/**", "/images/**", "/webjars/**", "**/favicon.ico", "/").permitAll()
                    .antMatchers("/static/html/**", "/js/**", "/jquery/**", "/js/plugins/layer/**", "/css/**", "/images/**", "/img/**", "/UPLOAD/img/**", "/mqtt/send").permitAll()
                    .antMatchers("/druid/").permitAll()
                    // 除上面外的所有请求全部需要鉴权认证
                    .anyRequest().authenticated();
            //所有请求都不需要权限就可以访问，这样的话所有请求内都无法得到认证信息，所以是anonymous
            // .anyRequest().permitAll();这里最好不要设置避免错误,因为上面的 http.antMatcher("/")是全站
        }
    }

}