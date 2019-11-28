package com.example.demo.entity.sys;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author longzhonghua
 * @createdata 3/7/2019 9:23 PM
 * @description 在线用户
 */
@Entity
@Data
public class MemberOnline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//由数据库控制,auto是程序统一控制
    private long id;
    /** 用户会话id */
    private String sessionId;
    /** session创建时间 */
    private long creationTimes;

    /** session最后访问时间 */
    private long lastAccessedTime;



    /** 部门名称 */
    private String deptName;

    /** 登录名称 */
    private String loginName;

    /** 登录IP地址 */
    private String ipaddr;

    /** 登录地址 */
    private String loginLocation;

    /** 浏览器类型 */
    private String browser;

    /** 操作系统 */
    private String os;



    /** 超时时间，单位为分钟 */
    private Long expireTime;

    private String referer;
    private String accept;
    private String method;
    private String url;
    private String querystring;
    /** 在线状态 */

}
