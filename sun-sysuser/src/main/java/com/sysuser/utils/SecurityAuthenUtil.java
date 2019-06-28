package com.sysuser.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.sysuser.model.sys.UserInfo;

public class SecurityAuthenUtil {
	
	public static final String ANONYMOUSUSER = "anonymousUser";
	/**
	 * 直接获取当前用户的登录账号
	 * @return
	 */
	public static Long getUserId() {
		//Authentication authenObj = SecurityContextHolder.getContext().getAuthentication();
		//return authenObj.getName();
		//UserInfo authenUser = (UserInfo)authenObj.getPrincipal();
		//return authenUser.getUsername();
		
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Long userId = null;
	    if (auth == null) {
	      if (userId == null) {
	    	  userId = 0L;
	      }
	      return userId;
	    }
	    if (auth == null || ANONYMOUSUSER.equalsIgnoreCase(auth.getName())) {
	      if (userId == null) {
	    	  userId = 0L;
	      }
	    } else {
	    	userId = new Long(auth.getName());
	    }
	    return userId;
	}
	
	/**
	 * 直接获取当前用户的登录登录名称
	 * @return
	 */
	public static String getLoginName() {
		Authentication authenObj = SecurityContextHolder.getContext().getAuthentication();
		String loginName="";
		 if (authenObj == null || ANONYMOUSUSER.equalsIgnoreCase(authenObj.getName())) {
			 loginName="";
		 } else {
			 UserInfo authenUser = (UserInfo)authenObj.getPrincipal();
			 loginName=authenUser.getLoginName();
		 }
		return loginName;
	}
	
	/**
	 * 直接获取当前用户的认证用户信息
	 * @return
	 */
	public static User getAuthenticationUser() {
		Authentication authenObj = SecurityContextHolder.getContext().getAuthentication();
		User authenUser = (User)authenObj.getPrincipal();
		return authenUser;
	}
	
	/**
	 * 直接获取当前登录角色
	 * @return
	 */
	public static int getRoleId() {
		Authentication authenObj = SecurityContextHolder.getContext().getAuthentication();
		int roleId=0;
		 if (authenObj == null || ANONYMOUSUSER.equalsIgnoreCase(authenObj.getName())) {
			 roleId=0;
		 } else {
			 UserInfo authenUser = (UserInfo)authenObj.getPrincipal();
			 roleId=authenUser.getRoleId();
		 }
		return roleId;
	}

}
