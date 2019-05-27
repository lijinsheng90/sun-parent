package com.customer.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserService {
    @Autowired
    RestTemplate restTemplate;            // 负载均衡ribbon对象
    
    // 熔断错误回调方法
    public String helloFallBack(){
        return "Error occurred!";
    }
    
    /**
     * 调用Eureka系统中名都为sys-sysuser的sys-sysuser或sys-order的方法/getUser
     * @return
     */
    // 注解指定发生错误时的回调方法
    @HystrixCommand(fallbackMethod="helloFallBack")
    public String getUserService(){
        // Get请求调用服务，restTemplate被@LoadBalanced注解标记，Get方法会自动进行负载均衡
        // restTemplate会交替调用sys-sysuser或sys-order
        return restTemplate.getForObject("http://sys-sysuser/getUser", String.class);
    }

}
