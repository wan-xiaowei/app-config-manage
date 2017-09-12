package com.wxw.admin.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wxw.web.feign.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:Created by wanxiaowei
 * @Description:
 * @Date:Created in 16:18 2017/8/9
 * @Modified By :
 */
@RestController
public class FeignController {
    @Autowired
    private FeignService feignService;

    /**
     * 默认5s内20次失败 Hystrix的状态up->circuit_open
     * execution.isolation.strategy = SEMAPHORE  or   THREAD
     * @return
     */
    @GetMapping("/feign")
    @HystrixCommand(fallbackMethod = "findFallBack",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000"),
            @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds",value = "10000"),
            @HystrixProperty(name="execution.isolation.strategy",value = "THREAD")
    },threadPoolProperties = {
            @HystrixProperty(name="coreSize",value = "1"),
            @HystrixProperty(name="maxQueueSize",value = "10")
    })
    public Object findByIdFeign() {
        Object obj = feignService.getAA();
        return obj;
    }

    public String findFallBack(){
        return "1";
    }
}
