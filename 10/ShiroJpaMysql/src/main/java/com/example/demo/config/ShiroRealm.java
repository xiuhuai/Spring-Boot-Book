package com.example.demo.config;

import com.example.demo.dao.AdminDao;
import com.example.demo.entity.Admin;
import com.example.demo.entity.SysPermission;
import com.example.demo.entity.SysRole;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

public class ShiroRealm extends AuthorizingRealm {
    @Resource
    private AdminDao adminDao;

    @Override
    /**
     * 权限配置
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //拿到用户信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Admin adminInfo = (Admin) principals.getPrimaryPrincipal();

        for (SysRole role : adminInfo.getRoleList()) {
            //将角色放入SimpleAuthorizationInfo
            info.addRole(role.getRole());
            //用户拥有的权限
            for (SysPermission p : role.getPermissions()) {
                info.addStringPermission(p.getPermission());
            }
        }
        return info;
    }

    /**
     * 进行身份认证,判断用户名密码是否匹配正确
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //获取用户的输入的账号
        String username = (String) token.getPrincipal();
        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //Shiro有时间间隔机制，2分钟内不会重复执行该方法
        //获取用户信息
        Admin adminInfo = adminDao.findByUsername(username);

        if (adminInfo == null) {
            return null;
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                /**
                 * 用户名
                 */
                adminInfo,
                /**
                 * 密码
                 */
                adminInfo.getPassword(),
                ByteSource.Util.bytes(adminInfo.getCredentialsSalt()),
                /**
                 * realm name
                 */
                getName()
        );
        return info;
    }

}