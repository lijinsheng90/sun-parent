package com.sysuser;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MyWebMvcConfig extends WebMvcConfigurationSupport {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/jquery/**").addResourceLocations("classpath:/static/jquery/");
		registry.addResourceHandler("/juqeryEasyUI/**").addResourceLocations("classpath:/static/juqeryEasyUI/");
	}

}
