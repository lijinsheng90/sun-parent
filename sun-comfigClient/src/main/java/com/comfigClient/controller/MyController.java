package com.comfigClient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	@Value("${foo}")
    private String foo;

    @RequestMapping("/foo")
    public String hi(){
        return foo;
    }
}
