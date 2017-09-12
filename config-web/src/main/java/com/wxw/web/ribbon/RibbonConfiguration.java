package com.wxw.web.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 不能被ComponentScan扫描，否则会被所有的@RibbonClient共享
 * Created by admin on 2017/8/9.
 */
@Configuration
public class RibbonConfiguration {
    @Bean
    public IRule ribbonRule(){
        //负载均衡，把默认轮循改为随机
        return new RandomRule();
    }
}
