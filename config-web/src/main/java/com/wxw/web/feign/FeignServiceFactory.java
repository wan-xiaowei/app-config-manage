package com.wxw.web.feign;


import com.wxw.web.feign.service.FeignService;
import feign.hystrix.FallbackFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 打印回退日志
 * Created by admin on 2017/8/11.
 */
@Component
public class FeignServiceFactory implements FallbackFactory<FeignService>{
        private static final Logger loggger = Logger.getLogger(FeignServiceFactory.class);
    @Override
    public FeignService create(Throwable throwable) {
        return new FeignService(){
            //日志最好放在fallback方法,不要直接放在create方法,否则在引用启动就会打印日志.
            @Override
            public Object getAA() {
                loggger.info("fallback:reason is {}",throwable);
                return null;
            }

           @Override
            public Object getBB(@RequestParam Map map) {
                return null;
            }

        };
    }
}
