package com.wxw.web.convert;


import com.wxw.config.ConfigConstant;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * 日期转换器 Created by heweiming on 2017/5/19.
 */
public class StringToDateConvert implements Converter<String, Date> {

    private static final Logger logger = LoggerFactory.getLogger(StringToDateConvert.class);

    @Override
    public Date convert(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        try {
            Date date = DateUtils.parseDate(source, ConfigConstant.DATE_FORMAT_PATTERN);
            return date;
        } catch (ParseException e) {
            logger.error("解析日期字符串 {} 失败， {} 格式不匹配", source, ConfigConstant.DATE_FORMAT_PATTERN);
            throw new IllegalArgumentException(
                    String.format("解析日期字符串 %s 失败， %s 格式不匹配", source, ConfigConstant.DATE_FORMAT_PATTERN));
        }
    }
    
}
