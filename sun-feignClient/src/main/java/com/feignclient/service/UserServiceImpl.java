package com.feignclient.service;

import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

	@Override
	public String getUserByName(String name) {
		// TODO Auto-generated method stub
		return "sorry,"+name;
	}

}
