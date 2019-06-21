package com.sysuser.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.sysuser.oauth2.filter.MySecurityFilter;
import com.sysuser.oauth2.logout.MyLogoutSuccessHandler;

//Spring-Security 配置
@Configuration
@EnableWebSecurity
@Order(1)
public class WebsecurityConfig extends WebSecurityConfigurerAdapter {

	//通过自定义userDetailsService 来实现查询数据库
	@Autowired
	private MyUserDetailsService userDetailsService;
	
    @Autowired  
    private MySecurityFilter mySecurityFilter;
    
	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	
	 /**
     * 让Security 忽略这些url，不做拦截处理
     *
     * @param
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
       web.ignoring().antMatchers
                ("/swagger-ui.html/**", "/wh/static/**",
                        "/swagger-resources/**", "/v2/api-docs/**",
                        "/swagger-resources/configuration/ui/**", "/swagger-resources/configuration/security/**",
                        "/images/**");
    }
    
/*	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //可以设置内存指定的登录的账号密码,指定角色
        //不加.passwordEncoder(new MyPasswordEncoder())
        //就不是以明文的方式进行匹配，会报错
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
        //.passwordEncoder(new MyPasswordEncoder())。
        //这样，页面提交时候，密码以明文的方式进行匹配。
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("admin").password("admin").roles("ADMIN");
    }*/
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
/*		http.authorizeRequests().anyRequest().authenticated();
		http.formLogin().failureUrl("/login?error").permitAll();
		http.logout().permitAll();
		http.csrf().disable();*/
		
		/* http  
	        .addFilterBefore(mySecurityFilter, FilterSecurityInterceptor.class);
		//http.authorizeRequests().anyRequest().fullyAuthenticated();
		http.formLogin().loginPage("/login").failureUrl("/login?code=").permitAll();
		http.logout().permitAll();
		http.authorizeRequests().antMatchers("/oauth/authorize").permitAll();*/
	
/*		 http.addFilterBefore(mySecurityFilter, FilterSecurityInterceptor.class)
	        .authorizeRequests().anyRequest().authenticated();//限定签名成功的请求
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
		      .and().formLogin().loginPage("/auth/login") .failureUrl("/auth/login?error").defaultSuccessUrl("/main").permitAll() //登录页面用户任意访问 
		      .and().logout().logoutSuccessHandler(new MyLogoutSuccessHandler()).permitAll(); //注销行为任意访问
		 http.authorizeRequests().antMatchers("/authorize","/jquery/**").permitAll();
		 
		 http.addFilterBefore(mySecurityFilter, FilterSecurityInterceptor.class);

	}
	
    /**
      * 用户验证
      */
     @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	 auth.userDetailsService(userDetailsService()); //user Details Service验证
         super.configure(auth);
     }
	

	// 配置内存模式的用户
	/*
	 * @Bean
	 * 
	 * @Override protected UserDetailsService userDetailsService(){
	 * InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	 * manager.createUser(User.withUsername("test").password("123").authorities(
	 * "USER").build());
	 * manager.createUser(User.withUsername("test1").password("123").authorities(
	 * "USER").build()); return manager; }
	 */
	
     /**
      * Spring Boot 2 配置，这里要bean 注入
      * 需要配置这个支持password模式 support password grant type
      */
     @Bean
     @Override
     public AuthenticationManager authenticationManagerBean() throws Exception {
         AuthenticationManager manager = super.authenticationManagerBean();
         return manager;
     }
     
}
