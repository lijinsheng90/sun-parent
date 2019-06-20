package com.sysuser.oauth2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.sysuser.oauth2.exception.MyAccessDeniedHandler;
import com.sysuser.oauth2.filter.MySecurityFilter;


@Configuration
@EnableResourceServer
@Order(6)
public class MyResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {

	
     private static final String DEMO_RESOURCE_ID = "order";
     
    @Autowired  
    private MySecurityFilter mySecurityFilter;
    
    //需要将两个自定义的异常处理类配置到资源资源服务中
    @Autowired
    private MyAccessDeniedHandler handler;
    @Autowired
    private AuthenticationEntryPoint point;
    
     
     @Override
     public void configure(ResourceServerSecurityConfigurer resources) {
         resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
         // 异常处理
         resources.authenticationEntryPoint(point).accessDeniedHandler(handler);
     }
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
/*		http.
		authorizeRequests()
		.antMatchers("/usernamepassword/token").permitAll()
		.antMatchers("/users/**","/menus/**","/roles/**").hasRole("ADMIN")
		.anyRequest()
		.authenticated();
		*/
		
/*         http.addFilterBefore(mySecurityFilter, FilterSecurityInterceptor.class)
         .authorizeRequests()//限定签名成功的请求
         .anyRequest().authenticated();
        //.antMatchers("/usernamepassword/token").permitAll()
        //.antMatchers("/users/**","/menus/**","/roles/**","/getUser/**").hasRole("ADMIN")
        //.antMatchers("/getUser/**").hasAnyRole("TEST2","ADMIN")//签名成功后可访问，并且受role限制
        //.antMatchers("/user/*").authenticated()//签名成功后可访问，不受role限制
        //.antMatchers("/oauth/token").permitAll()
        //.anyRequest().permitAll()//其他没有限定的请求，允许访问
        //.anyRequest().authenticated()
        //.and().anonymous()//对于没有配置权限的其他请求允许匿名访问
        //.and().formLogin()//使用 spring security 默认登录页面
        //.and().httpBasic()//启用http 基础验证
        //.and().csrf().disable();//关闭跨站请求防护
*/
		http.authorizeRequests() 
	      .anyRequest().authenticated() //任何请求,登录后可以访问 
	      .and() .formLogin() .loginPage("/auth/login") .failureUrl("/auth/login?error") .permitAll() //登录页面用户任意访问 
	      .and() .logout().permitAll(); //注销行为任意访问 
		 http.authorizeRequests().antMatchers("/authorize").permitAll();
	        
	     http.addFilterBefore(mySecurityFilter, FilterSecurityInterceptor.class);

	}
}
