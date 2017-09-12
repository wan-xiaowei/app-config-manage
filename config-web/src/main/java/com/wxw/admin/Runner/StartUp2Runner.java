package com.wxw.admin.Runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author:Created by wanxiaowei
 * @Description:
 * @Date:Created in 14:06 2017/9/1
 * @Modified By :
 */
@Component
@Order(value =  2)
public class StartUp2Runner implements CommandLineRunner {
    public static final Logger logger = LoggerFactory.getLogger(StartUp2Runner.class);
    @Override
    public void run(String... strings) throws Exception {
        logger.info("start up 2-------------");
    }
}
