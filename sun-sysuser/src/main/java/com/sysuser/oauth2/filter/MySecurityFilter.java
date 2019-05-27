package com.sysuser.oauth2.filter;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sysuser.oauth2.MyAccessDecisionManager;
import com.sysuser.oauth2.MyFilterInvocationSecurityMetadataSource;


//@Component
@Service
public class MySecurityFilter extends AbstractSecurityInterceptor implements Filter{

    @Autowired  
    private MyFilterInvocationSecurityMetadataSource  mySecurityMetadataSource;
    
    @Autowired  
    private MyAccessDecisionManager myAccessDecisionManager;
    
	@Autowired
	public void setMyAccessDecisionManager(MyAccessDecisionManager myAccessDecisionManager) {
		super.setAccessDecisionManager(myAccessDecisionManager);
	}

    
/*    @PostConstruct
    public void init() {
    	 super.setAuthenticationManager(authenticationManager);  
	     super.setAccessDecisionManager(myAccessDecisionManager); 
    }*/
    
    
    
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("filter===========================");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation( request, response, chain );  
        invoke(fi);
		
	}
	
	 public void invoke( FilterInvocation fi ) throws IOException, ServletException{
		//fi里面有一个被拦截的url 
		//里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限 
		 //再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
		 InterceptorStatusToken  token = super.beforeInvocation(fi);  
		 try{  
			 fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		 }finally{  
	            super.afterInvocation(token, null);  
	     }
		 
	 }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub 
		System.out.println("filter===========================end");
	}

	@Override
	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		// TODO Auto-generated method stub
		return this.mySecurityMetadataSource;
	}
	
	

}
