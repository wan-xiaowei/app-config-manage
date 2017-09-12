package com.wxw.web.feign;

import com.wxw.web.feign.service.FeignService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by admin on 2017/8/11.
 */
@Component
public class FeignServiceFallBack implements FeignService {
    @Override
    public Object getAA() {
        return "aa";
    }

    @Override
    public Object getBB(@RequestParam Map map) {
        return null;
    }
}
