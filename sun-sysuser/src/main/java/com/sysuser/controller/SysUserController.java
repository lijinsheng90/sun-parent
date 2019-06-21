package com.sysuser.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sysuser.utils.SecurityAuthenUtil;


@RestController
public class SysUserController {
	
	private String loginProcessUrl="/wh/auth/authorize";
	
    @RequestMapping(value="/getUser",method=RequestMethod.POST)
    public String getUser(){
        return "Hello world AAAA getUser!";
    }
    
    @RequestMapping(value="/getUserByName",method=RequestMethod.GET)
	public String getUserByName(@RequestParam String name) {
		return "SysUserController AAAA name :"+name;
	}

    @GetMapping("/auth/login")
    public ModelAndView loginPage(Model model){
    	ModelAndView view = new ModelAndView();
    	if(SecurityAuthenUtil.getUserId()!=0) {
    		view.addObject("userid", SecurityAuthenUtil.getUserId());
            view.addObject("LoginName", SecurityAuthenUtil.getLoginName());
            view.setViewName("main");
    	}else {
    		view.setViewName("base-login");
            view.addObject("loginProcessUrl", loginProcessUrl);
    	}
        return view;
    }
    
    @RequestMapping(value="/main")
    public ModelAndView main(Model model){
        ModelAndView view = new ModelAndView();
        view.addObject("userid", SecurityAuthenUtil.getUserId());
        view.addObject("LoginName", SecurityAuthenUtil.getLoginName());
        view.setViewName("main");
        return view;
    }
}
