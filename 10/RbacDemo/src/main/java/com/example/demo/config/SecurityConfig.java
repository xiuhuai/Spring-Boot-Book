package com.example.demo.config;
import com.example.demo.Service.SysUser.SysSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailHander;
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();   // 使用 BCrypt 加密
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/admin/**").//这里必须要加,不然走这里了,就不进行下面的几个验证了
                    //指定登录认证的Controller
                            formLogin().usernameParameter("uname").passwordParameter("pwd").loginPage("/admin/login").successHandler(
                    myAuthenticationSuccessHandler).failureHandler(myAuthenticationFailHander)
                    .and()
                    .authorizeRequests()
                    //登录相关
                    .antMatchers("/admin/login").permitAll()
                    //  .antMatchers("/admin/**").permitAll()//.hasRole("ADMIN")
                    //rabc相关
                    .antMatchers("/admin/rbac").access("@rbacService.hasPermission(request,authentication)")

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

