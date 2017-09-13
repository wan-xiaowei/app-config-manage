package com.wxw.web.ribbon;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * Created by admin on 2017/8/9.
 */
@Configuration
@RibbonClient(name = "cia-j",configuration = RibbonConfiguration.class)
public class TestConf {
}
