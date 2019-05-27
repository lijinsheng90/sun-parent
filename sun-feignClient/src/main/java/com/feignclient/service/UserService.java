package com.feignclient.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.feignclient.comfig.FeignConfig;

@FeignClient(value = "sys-sysuser", fallback = UserServiceImpl.class,configuration = FeignConfig.class)
public interface UserService {
	@RequestMapping(value = "/getUserByName")
	String getUserByName(@RequestParam(value = "name") String name);
}
