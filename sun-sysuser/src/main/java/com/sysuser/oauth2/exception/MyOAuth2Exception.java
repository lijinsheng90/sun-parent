package com.sysuser.oauth2.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

@JsonSerialize(using=MyOAuthExceptionJacksonSerializer.class)
public class MyOAuth2Exception extends OAuth2Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2533535971748555294L;

	public MyOAuth2Exception(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
    public MyOAuth2Exception(String msg, Throwable t) {
        super(msg, t);
    }


}
