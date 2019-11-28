package com.example.demo.config;

import com.example.demo.service.MethodSecurityService;
import com.example.demo.service.impl.MethodSecurityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;





/*
 * @EnableGlobalMethodSecurity(securedEnabled=true)
 * 开启@Secured 注解过滤权限
 * 
 * @EnableGlobalMethodSecurity(jsr250Enabled=true)
 * 开启@RolesAllowed 注解过滤权限
 * 
 * @EnableGlobalMethodSecurity(prePostEnabled=true)
 * 使用表达式时间方法级别的安全性 4个注解可用
 * -@PreAuthorize 在方法调用之前,基于表达式的计算结果来限制对方法的访问
 * -@PostAuthorize 允许方法调用,但是如果表达式计算结果为false,将抛出一个安全性异常
 * -@PostFilter 允许方法调用,但必须按照表达式来过滤方法的结果
 * -@PreFilter 允许方法调用,但必须在进入方法之前过滤输入值
 **/
@Configuration
@EnableWebSecurity//指定为Spring Security配置类
 
@EnableGlobalMethodSecurity(prePostEnabled = true,jsr250Enabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { 
	@Bean
	public MethodSecurityService methodSecurityService() {
		return new MethodSecurityServiceImpl();
		}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	return super.authenticationManagerBean();
	}
	
@Override
protected void configure(HttpSecurity http) throws Exception {
http.authorizeRequests()
.antMatchers("/*", "/welcome","/login").permitAll()
.antMatchers("/home").hasRole("ADMIN")
.anyRequest().authenticated()
.and()
.formLogin().loginPage("/login").defaultSuccessUrl("/home")
.and()
.logout().permitAll();}
//spring security 版本在5.0后就要加个PasswordEncoder,否则出错,由于是演示,所以添加下面代码解决,不过官方不建议这样处理.
@Bean
public static NoOpPasswordEncoder passwordEncoder() {
    return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
}
@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
auth.inMemoryAuthentication()
.withUser("admin").password("123456").roles("ADMIN")
.and().withUser("user").password("123456").roles("USER");}

}
