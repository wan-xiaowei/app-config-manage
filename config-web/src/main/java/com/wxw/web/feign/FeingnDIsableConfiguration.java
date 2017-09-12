package com.wxw.web.feign;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 想要禁用Hystrix的@FeignClient引用该配置类即可, 全部禁用FeignClient,设置feign.hystrix.enabled : false
 * Created by admin on 2017/8/11.
 */
@Configuration
public class FeingnDIsableConfiguration {
    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder(){
        return  Feign.builder();
    }
}
