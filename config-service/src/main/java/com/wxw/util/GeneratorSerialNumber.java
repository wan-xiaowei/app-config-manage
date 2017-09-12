package com.wxw.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class GeneratorSerialNumber {
    private static final Logger logger = LoggerFactory.getLogger(GeneratorSerialNumber.class);

    private String currentMaxSerialNumber;

    /**
     * 数据库表名
     */
    private final String dataBase = "legalprotocol";

    private final String sql = "select max(ProtocolCode) from legalprotocol ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*public GeneratorSerialNumber(SerialNumberType serialNumberType) {
        switch (serialNumberType.ordinal()){
            //店铺
            case 1 :
                dataBase = "legalshop";
                break;

            //收付款账号
            case 2 :
                dataBase = "";
                break;
            //协议
            default :
                dataBase = "legalprotocol";
                break;

        }
    }*/
    public void init() {
        try {
            currentMaxSerialNumber = jdbcTemplate.queryForObject(sql, String.class);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("生成编号出错", e);
            }
        }
    }

   /* public synchronized String next(SerialNumberType serialNumberType) {
        try {
            String date = DateUtils.dateToStr(new Date(), "yyyyMMdd");
            if (currentMaxSerialNumber.isEmpty()) {
                currentMaxSerialNumber = date + String.format("%03", 1);
                return currentMaxSerialNumber;
            }
            String number = currentMaxSerialNumber.substring(7);
            String day = currentMaxSerialNumber.substring(0, 7);
            boolean status = (day.equals(date) ? true : false);
            if (status) {
                int digit = Integer.parseInt(number);
                ++digit;
                currentMaxSerialNumber = day + String.format("%03d", digit);
            } else {
                currentMaxSerialNumber = date + String.format("%03d", 1);
            }
            return serialNumberType + currentMaxSerialNumber;
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("生成编号出错", e);
            }
        }
        return "";
    }*/
}
