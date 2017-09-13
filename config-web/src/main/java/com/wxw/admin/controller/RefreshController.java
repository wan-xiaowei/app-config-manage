package com.wxw.admin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:Created by wanxiaowei
 * @Description:
 * @Date:Created in 1:47 2017/8/21
 * @Modified By :
 */
@RestController
@RefreshScope   //手动刷新配置(post请求:localhost:8090/cia-j/refresh)
public class RefreshController {
   /* @Value("${profile}")*/
    private String profile;

    @GetMapping("/profile")
    public  String hello(){
        return  profile;
    }
}
