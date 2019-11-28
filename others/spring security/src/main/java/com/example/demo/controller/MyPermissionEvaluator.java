 package com.example.demo.controller;

import java.io.Serializable;
import java.util.Collection;

 
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class MyPermissionEvaluator implements PermissionEvaluator {

	 

	   public boolean hasPermission(Authentication authentication,

	         Object targetDomainObject, Object permission) {

	      if ("user".equals(targetDomainObject)) {

	         return this.hasPermission(authentication, permission);

	      }

	      return false;

	   }

	 

 

	   public boolean hasPermission(Authentication authentication,

	         Serializable targetId, String targetType, Object permission) {

	      return true;

	   }

	  

 

	   private boolean hasPermission(Authentication authentication, Object permission) {

	      Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

	      for (GrantedAuthority authority : authorities) {

	         if (authority.getAuthority().equals(permission)) {

	            return true;

	         }

	      }

	      return false;

	   }

	 

	} 