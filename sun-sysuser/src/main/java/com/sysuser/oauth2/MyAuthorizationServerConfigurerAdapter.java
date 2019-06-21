package com.sysuser.oauth2;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.sysuser.oauth2.filter.MyBasicAuthenticationFilter;

@Configuration
@EnableAuthorizationServer
public class MyAuthorizationServerConfigurerAdapter extends AuthorizationServerConfigurerAdapter {

    
    @Autowired
    private MyBasicAuthenticationFilter filter;
    
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
		/*设置签名*/
		accessTokenConverter.setSigningKey("smallsnail");
		return accessTokenConverter;
	}
	
	//权限验证控制器
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private RedisConnectionFactory redisConnection;
	
	@Autowired
    private DataSource dataSource;
	
	@Bean 
    public ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }
	
	 
	 @Autowired
     private WebResponseExceptionTranslator myWebResponseExceptionTranslator;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		//super.configure(security);
		security.addTokenEndpointAuthenticationFilter(filter);
		security.allowFormAuthenticationForClients()
		                    .tokenKeyAccess("isAuthenticated()")
		                     .checkTokenAccess("permitAll()");
		// 允许表单登录
        //security.allowFormAuthenticationForClients();
	}

	//用来配置授权（authorizatio）以及令牌（token）的访问端点和令牌服务   核心配置  在启动时就会进行配置
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		/*redis存储token*/
		/*endpoints.authenticationManager(authenticationManager).tokenStore(new MyRedisTokenStore(redisConnection));*/
		/*jwt方式*/
		/*endpoints.accessTokenConverter(jwtAccessTokenConverter());
		endpoints.authenticationManager(authenticationManager).tokenStore(new JwtTokenStore(jwtAccessTokenConverter()));*/
		/*jwt方式+redis存储token*/
		
		//endpoints.accessTokenConverter(jwtAccessTokenConverter());
		endpoints.authenticationManager(authenticationManager).tokenStore(new MyRedisTokenStore(redisConnection));////开启密码授权类型 和 配置token存储方式
		//endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore);
		// 处理 ExceptionTranslationFilter 抛出的异常
		endpoints.exceptionTranslator(myWebResponseExceptionTranslator);
		endpoints.pathMapping("/wh/oauth/confirm_access","/wh/custom/confirm_access");
		endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST); //设置允许get,post
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(clientDetailsService());
	}

}
