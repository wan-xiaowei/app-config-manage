package com.wxw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.wxw.web.feign.service") //1.加上注解扫描包,否则可能会出现FeignService无法被注入
//@ComponentScan(basePackages={"com.wxw.web.feign"})  //2.如果有fallback 需要 加上@ComponentScan(basePackages={"com.wxw.web.feign"}) 才能启动,否则会加载不到实例
@EnableHystrix
public class AppConfigManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppConfigManageApplication.class, args);
    }
}

