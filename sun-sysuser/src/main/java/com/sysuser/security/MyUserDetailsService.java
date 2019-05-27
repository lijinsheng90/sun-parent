package com.sysuser.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.sysuser.dao.SysRoleDao;
import com.sysuser.dao.SysUserDao;
import com.sysuser.model.sys.SysAuthoritie;
import com.sysuser.model.sys.SysUser;
import com.sysuser.model.sys.UserInfo;


@Component
public class MyUserDetailsService implements UserDetailsService {
	Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);
	
	@Autowired
	SysUserDao userDao;
	
	@Autowired
	SysRoleDao roleDao;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser userEntity = userDao.getUserEntityByLoginName(username);
		if(userEntity == null) {
			throw new UsernameNotFoundException("用户名："+ username + "不存在！");
		}
		
/*		if (userEntity == null) {
			String errormsg = null;
			switch (1) {
			case 0:
				errormsg = "您登录的用户尚未激活或尚未在系统中注册";
				break;
			case -1:
				errormsg = "密码错误";
				break;
			case -2:
				errormsg = "您的注册邮箱地址不合法";
				break;
			case -3:
				errormsg = "用户暂时被禁用!需要解禁请联系管理员";
				break;
			default:
				break;
			}

			throw new AuthenticationServiceException(messageSource.getMessage(errormsg, null,
					LocaleContextHolder.getLocale()));
		}*/
		
		
		String password = userEntity.getPassword();
		List<GrantedAuthority> collection = new ArrayList <>();
		//Collection<GrantedAuthority> collection = new HashSet<GrantedAuthority>();
		List<SysAuthoritie> authorities=userDao.getAuthoritieByUserId(userEntity.getId(),0);//获取用户所用权限
		for (SysAuthoritie sysAuthoritie : authorities) {
			collection.add(new SimpleGrantedAuthority(sysAuthoritie.getName()));
		}
		
/*        Iterator<String> iterator =  roleDao.getRolesByUserId(userEntity.getId()).iterator();
        while (iterator.hasNext()){
            collection.add(new SimpleGrantedAuthority(iterator.next()));
        }*/
		
		// 以下属性,暂时全部设为true,看不同的需求再次修改.  
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		boolean enabled = true;
		
		UserInfo userInfo = new UserInfo(userEntity.getId()+"", password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, collection);
		userInfo.setId(userEntity.getId());
		userInfo.setLoginName(userEntity.getLoginName());
		return userInfo;
		/*return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));*/
		//return new User(username, password, collection);
	}

}
