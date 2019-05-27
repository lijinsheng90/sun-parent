package com.sysuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class SunSysuserApp {
    public static void main(String[] args) {
        SpringApplication.run(SunSysuserApp.class, args);
    }
}
