/**
 * $Id: DateUtils.java 2015年12月19日 上午11:16:06 liaoyongchao
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wxw.util;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class DateUtils {

    /**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long
     */
    public static long getDaySub(String beginDateStr, String endDateStr, String rule) {
        long days = 0;
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(rule);
            LocalDateTime beginDate = LocalDateTime.parse(beginDateStr, df);
            LocalDateTime endDate = LocalDateTime.parse(endDateStr, df);
            Duration duration = Duration.between(beginDate, endDate);
            days = duration.toDays();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * <li>功能描述：时间相减得到天数
     * @param beginDate
     * @param endDate
     * @return
     * long
     */
    public static long getDaySub(Date beginDate, Date endDate) {
        long days = 0;
        try {
            Duration duration = Duration.between(beginDate.toInstant(), endDate.toInstant());
            days = duration.toDays();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 日期转换成字符串
     * @param date
     * @return str
     */
    /*
    public static String dateToStr(Date date, String formatStr) {

        String str = "";
        if (date != null) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(formatStr);
            str = df.format(date.toInstant());
        }
        return str;
    }*/
    public static String dateToStr(Date date, String formatStr) {

        String str = "";
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat(formatStr);
            str = format.format(date);
        }
        return str;
    }

    public static void main(String args[]) {
        System.out.println(new Date().getTime());
    }

    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date StrToDate(String str, String formatStr) {
        Date date = null;
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(formatStr);
            LocalDateTime dateTime = LocalDateTime.parse(str, df);
            ZoneId zone = ZoneId.systemDefault();
            Instant instant = dateTime.atZone(zone).toInstant();
            date=Date.from(instant);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;

       /* SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;*/
    }

    /**
     * 获取当天0点的时间
     * @return
     */
    public static Date getDayBeginTime() {
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime=LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        Instant instant=localDateTime.atZone(zone).toInstant();
        return Date.from(instant);

       /* Date date = new Date();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        Date date2 = new Date(date.getTime() - gc.get(Calendar.HOUR_OF_DAY) * 60 * 60
                * 1000 - gc.get(Calendar.MINUTE) * 60 * 1000 - gc.get(Calendar.SECOND)
                * 1000);
        return new Date(date2.getTime());*/
    }

    /**
     * 得到数天后前(后)的时间
     * @param d
     * @param day
     * @return
     */
    public static Date getNewDate(Date d, int day) {
        if (d == null) return null;
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime dateTime = LocalDateTime.ofInstant(d.toInstant(),zone);
        Instant instant = dateTime.plusDays(day).atZone(zone).toInstant();
        return Date.from(instant);


       /* if (d == null) return null;
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();*/
    }

    /**
     * 获得数天后前(后)0点的时间
     * @param d
     * @param day
     * @return
     */
    public static Date getServelDayBeginTime(Date d, int day) {

        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime=LocalDateTime.ofInstant(d.toInstant(),zone).plusDays(day).withHour(0).withMinute(0).withSecond(0).withNano(0);
        Instant instant=localDateTime.atZone(zone).toInstant();
        return Date.from(instant);

      /*  Date date = getNewDate(d, day);
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        Date date2 = new Date(date.getTime() - gc.get(Calendar.HOUR_OF_DAY) * 60 * 60
                * 1000 - gc.get(Calendar.MINUTE) * 60 * 1000 - gc.get(Calendar.SECOND)
                * 1000);
        return new Date(date2.getTime());*/
    }

    /**
     * 数分钟前的时间（Date）
     * @param d
     * @param minutes
     * @return
     */
    public static Date getMinutesAgoTime(Date d, int minutes) {
        if (d == null) return null;
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime dateTime = LocalDateTime.ofInstant(d.toInstant(),zone);
        Instant instant = dateTime.plusMinutes(minutes).atZone(zone).toInstant();
        return Date.from(instant);


       /* Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) + minutes);
        return now.getTime();*/
    }

    /**
     * long转Date
     * @param date
     * @return
     */
    public static Date longTODate(long date) {
        if (date == 0) {
            return null;
        } else {
            return new Date(date);
        }

    }

    public static String dateTOSolrDate(Date date) {
        return DateUtils.dateToStr(date, "yyyy-MM-dd'T'HH:mm:ss'Z'");
    }
}
