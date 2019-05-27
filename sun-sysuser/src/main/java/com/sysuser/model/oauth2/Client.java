package com.sysuser.model.oauth2;

import lombok.Data;


/**
 * @author yuit
 * @date 2018/10/16 9:23
 *
 **/
@Data
public class Client {

	private String clientId;
	
	private String resourceIds;

	private String clientSecret;
	

	private String scope;
	
	private String authorizedGrantTypes;
	
	private String registeredRedirectUri;
	
	private String authorities;
	

}
