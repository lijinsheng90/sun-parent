package com.eurekacentre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer                    // 开启eureka服务
@SpringBootApplication
public class SunEurekacentreApp {
    public static void main(String[] args) {
        SpringApplication.run(SunEurekacentreApp.class, args);
    }
}
