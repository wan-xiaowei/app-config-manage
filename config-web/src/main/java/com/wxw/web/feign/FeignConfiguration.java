package com.wxw.web.feign;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 不能被ComponentScan扫描，否则会被所有的@RibbonClient共享(默认契约:SpringMvcContract)
 * Created by admin on 2017/8/9.
 */
@Configuration
public class FeignConfiguration {
    /**
     * 默认SpringMvcContract(可用spring注解,若改为default用RequestLine注解)
     * @return
     */
   /* @Bean
    public Contract feignContract(){
        //负载均衡，修改为默认契约
        return new Contract.Default();
    }*/

    /**
     * 日志级别:1.NONE:不记录任何值(默认);2.BASIC:记录请求方法.URL.响应状态代码,执行时间;3.HEADERS:记录basic级别基础上,还记录请求和响应的header;4.FULL:记录请求和响应的header,body和元数据
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
