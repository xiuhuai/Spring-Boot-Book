package com.example.demo.util.aop;

import java.lang.annotation.*;

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 * 日志注解类
 */
//@Target(ElementType.METHOD)
@Target({ElementType.PARAMETER, ElementType.METHOD})//目标是方法
@Retention(RetentionPolicy.RUNTIME)//注解会在class中存在，运行时可通过反射获取
@Documented//文档生成时，该注解将被包含在javadoc中，可去掉
public @interface LoggerManage {
	//模块名字
	public String description() default "logger null";
}
