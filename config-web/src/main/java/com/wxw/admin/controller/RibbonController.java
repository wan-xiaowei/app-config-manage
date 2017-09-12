package com.wxw.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author:Created by wanxiaowei
 * @Description:
 * @Date:Created in 11:29 2017/8/9
 * @Modified By :
 */
@RestController
public class RibbonController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/ribbon")
    public Object findById(Long id) {
        Object object = this.restTemplate.getForObject("http://internal-transfer/internal-transfer/transfer-record/conditionTransferInfoData?pageSize=2&pageIndex=1",Object.class);
        return object;
    }

    @GetMapping("/instance")
    public void getInstance(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("cia-j");
    }
}
