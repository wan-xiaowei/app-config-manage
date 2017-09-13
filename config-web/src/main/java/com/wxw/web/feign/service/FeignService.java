package com.wxw.web.feign.service;

import com.wxw.web.feign.FeignConfiguration;
import com.wxw.web.feign.FeignServiceFallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Author:Created by wanxiaowei
 * @Description:
 * @Date:Created in 16:27 2017/8/9
 * @Modified By :
 */
//url = "/internal-transfer/transfer-record/conditionTransferInfoData?pageSize=2&pageIndex=1"
    //可指定属性fallbackFactory = FeignServiceFactory.class   ,fallback = FeignServiceFallBack.class
@FeignClient( name = "internal-transfer",configuration = FeignConfiguration.class)
public interface FeignService {
     @RequestMapping(value="/getAA",method=RequestMethod.GET)
     Object getAA();
    /**
     * get只能用map,不能用实体，会报错
     * @param map
     * @return
     */
     @RequestMapping(value="/getBB", method=RequestMethod.GET)
     Object getBB(@RequestParam Map map);

}
