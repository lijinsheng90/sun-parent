package com.sysuser.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sysuser.common.RoleAnnotation;

@RestController
public class SysUserController {
	
    @RequestMapping(value="/getUser",method=RequestMethod.POST)
    public String getUser(){
        return "Hello world AAAA getUser!";
    }
    
    @RequestMapping(value="/getUserByName",method=RequestMethod.GET)
	public String getUserByName(@RequestParam String name) {
		return "SysUserController AAAA name :"+name;
	}

}
