package com.sysuser.oauth2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import com.sysuser.dao.SysMenuDao;
import com.sysuser.dao.SysUserDao;
import com.sysuser.model.sys.SysAuthoritie;
import com.sysuser.model.sys.SysMenu;

@Service
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{

	@Autowired
	SysUserDao userDao;
	
	@Autowired
	SysMenuDao sysMenuDao;
	
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	
	
	/**
     * 加载权限表中所有权限
     */
	@PostConstruct
	private void loadResourceDefine() {
		List<SysMenu> resoulesList=sysMenuDao.getResourcesList();
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		Collection<ConfigAttribute> array;
		ConfigAttribute cfg;
		for (SysMenu sysMenu : resoulesList) {
			if(resourceMap.containsKey(sysMenu.getUrl())) {
				array=resourceMap.get(sysMenu.getUrl());
			}else {
				array = new ArrayList<>();
			}
			for (SysAuthoritie sysAuthoritie : sysMenu.getSysAuthorities()) {
				cfg = new SecurityConfig(sysAuthoritie.getName());
				array.add(cfg);
				//用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value，
				resourceMap.put(sysMenu.getUrl(), array);
			}
		}
	}
	
	//此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		FilterInvocation filterInvocation = (FilterInvocation) object;
		if (resourceMap == null) {  
            loadResourceDefine();  
        }
		HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
		AntPathRequestMatcher matcher;
		String resUrl;
		for (Iterator<String> iter = resourceMap.keySet().iterator(); iter.hasNext();) {
			resUrl = iter.next();
			matcher = new AntPathRequestMatcher(resUrl);
			if (matcher.matches(request)) {
				return resourceMap.get(resUrl);
			}
		}
		return null;
		
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
