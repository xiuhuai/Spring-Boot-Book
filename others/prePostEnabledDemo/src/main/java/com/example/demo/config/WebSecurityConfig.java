package com.example.demo.config;

import com.example.demo.controller.MyPermissionEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */
//指定为配置类
@Configuration
//指定为Spring Security配置类,指定为Spring Security配置类，如果是WebFlux则需要启用@EnableWebFluxSecurity
@EnableWebSecurity
/**
*如果要启用方法安全设置，则开启此项。
*/
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 不拦截静态资源
        web.ignoring().antMatchers("/static/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 使用 BCrypt 加密
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().usernameParameter("uname").passwordParameter("pwd").loginPage("/admin/login").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();
        http.logout().permitAll();
        //记住我功能,并指定参数为rememberme
        http.rememberMe().rememberMeParameter("rememberme");
        // 处理异常，拒绝访问就重定向到 403 页面
        http.exceptionHandling().accessDeniedPage("/403");
        http.logout().logoutSuccessUrl("/");
        //配置CSRF保护，这里忽略文件上传的接口。
        http.csrf().ignoringAntMatchers("/admin/upload");
    }
    @Autowired
    private MyPermissionEvaluator  myPermissionEvaluator;

}
