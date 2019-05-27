package com.sysorder.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysOrderController {
	
    @RequestMapping("/getUser")
    public String getUser(){
        return "Hello world BBBB getUser!";
    }
    
    @RequestMapping(value="/getUserByName",method=RequestMethod.GET)
	public String getUserByName(@RequestParam String name) {
		return "SysUserController BBBB name :"+name;
	}
}
