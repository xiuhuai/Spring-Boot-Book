package com.example.demo.Service;

import com.example.demo.entity.sysuser.SysPermission;
import com.example.demo.entity.sysuser.SysRole;
import com.example.demo.entity.sysuser.SysUser;
import com.example.demo.repository.SysUser.SysPermissionRepository;
import com.example.demo.repository.SysUser.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * @author longzhonghua
 * @data 2/26/2019 9:29 AM
 */

@Component("rbacService")
public class RbacServiceImpl implements RbacService {
    private org.springframework.util.AntPathMatcher AntPathMatcher = new AntPathMatcher();
    @Autowired
    private SysPermissionRepository permissionRepository;
    @Autowired
    private SysUserRepository sysUserRepository;
     @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;

/**
 *这里应该注入用户和该用户所拥有的权限（权限在登录成功的时候已经缓存起来，当需要访问该用户的权限是，直接从缓存取出！）
 *然后验证该请求是否有权限，有就返回true，否则则返回false不允许访问该Url。
 *还传入了request,可以使用request获取该次请求的类型。
 *根据restful风格使用它来控制我们的权限
 *例如当这个请求是post请求，证明该请求是向服务器发送一个新建资源请求，
 *我们可以使用request.getMethod()来获取该请求的方式，然后在配合角色所允许的权限路径进行判断和授权操作！
 *如果能获取到Principal对象不为空证明，授权已经通过*/
        if (principal != null && principal instanceof UserDetails) {
            // 登录的用户名
            String userName = ((UserDetails) principal).getUsername();
            //获取请求登录的url
            Set<String> urls = new HashSet<>();//用户具备的系统资源集合，从数据库读取
            Set<String> curds = new HashSet<>();//用户具备的系统资源集合，从数据库读取
            SysUser sysUser = sysUserRepository.findByName(userName);
            try {
                for (SysRole role : sysUser.getRoles()) {
                   for (SysPermission permission : role.getPermissions()) {
                       urls.add(permission.getUrl());
                       //curds.add(permission.getPermission());
                       curds.add(permission.getPermission()+permission.getUrl());
                   }

               }
            } catch (Exception e) {
                e.printStackTrace();
            }


            //urls.add("/sys/user/add");

            for (String url : urls) {
                if (AntPathMatcher.match(url, request.getRequestURI())) {

                    hasPermission = true;
                    break;
                }
            }
        }

        return hasPermission;
    }
}
