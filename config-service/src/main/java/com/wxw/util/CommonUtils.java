/**
 * $Id: CommonUtils.java Feb 10, 2015 2:21:35 PM hdp
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wxw.util;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * 公共工具类
 */
public final class CommonUtils {

	/**
	 * 计数器
	 */
	private static short counter = (short) 0;
	/**
	 * JVM
	 */
	private static final int JVM = (int) (System.currentTimeMillis() >>> 8);
	

	/**
	 * 获取私密hash路径
	 * @param uuid uuid
	 * @return
	 */
	public static String getEmotionHashPath(String uuid) {
		int hashCode = uuid.hashCode();
		StringBuilder sb = new StringBuilder("emotion/");
		String tmp = format(hashCode);
		for (int i = 0; i < 4; i++) {
			sb.append(tmp.substring(i * 2, (i + 1) * 2)).append("/");
		}
		return sb.toString();
	}
	/**
	 * 获取随机uuid，不包含“-”符号的
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("\\-", "");
	}
	


	/**
	 * 将整型数据格式化为8位的16进制字符串
	 * @param intval 整型
	 * @return 8位的16进制字符串
	 */
	private final static String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuilder buf = new StringBuilder("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}
	/**
	 * 将整型数据格式化为4位的16进制字符串
	 * @param shortval 短整型
	 * @return 4位的16进制字符串
	 */
	private final static String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuilder buf = new StringBuilder("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}

	/**
	 * 获取JVM
	 * @return
	 */
	private final static int getJVM() {
		return JVM;
	}
	
	/**
	 * 获取计数器
	 * @return
	 */
	private final static short getCount() {
		synchronized (CommonUtils.class) {
			if (counter < 0)
				counter = 0;
			return counter++;
		}
	}


	/**
	 * Unique down to millisecond
	 */
	private final static short getHiTime() {
		return (short) (System.currentTimeMillis() >>> 32);
	}

	private final static int getLoTime() {
		return (int) System.currentTimeMillis();
	}

	/**
	 * 字节转为整型数据
	 * @param bytes 字节数组
	 * @return
	 */
	private final static int toInt(byte[] bytes) {
		int result = 0;
		for (int i = 0; i< 4; i++) {
			result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
		}
		return result;
	}
	/**
	 * 两个BigDecimal为null的时候数据相加
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static BigDecimal addBigDecimalValue(BigDecimal value1, BigDecimal value2){
		if(value1 == null) value1 = new BigDecimal(0);
		if(value2 == null) value2 = new BigDecimal(0);
		return value1.add(value2);
	}
	
	/**
	 * 两个BigDecimal为null的时候数据相减
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static BigDecimal subtractBigDecimalValue(BigDecimal value1, BigDecimal value2){
		if(value1 == null) value1 = new BigDecimal(0);
		if(value2 == null) value2 = new BigDecimal(0);
		return value1.subtract(value2);
	}
	
	/**
	 * 两个BigDecimal为null的时候数据相除
	 * @param value1
	 * @param value2
	 * @return
	 * @throws Exception 
	 */
	public static BigDecimal divideBigDecimalValue(BigDecimal value1, BigDecimal value2){
		if(value1 == null) value1 = new BigDecimal(0);
		if(value2 == null) value2 = new BigDecimal(0);
		
		if(value2.compareTo(BigDecimal.ZERO)!= 0){
			return value1.setScale(4, RoundingMode.HALF_UP).divide(value2, 4);
		}
		return new BigDecimal(0);
	}
	
	/**
	 * 两个BigDecimal为null的时候数据相乘
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static BigDecimal multiplyBigDecimalValue(BigDecimal value1, BigDecimal value2){
		if(value1 == null) value1 = new BigDecimal(0);
		if(value2 == null) value2 = new BigDecimal(0);
		
		return value1.multiply(value2);
	}
	
	public static String Split(String filed,String match,int i){
				if(filed != null)
					return filed.split(match)[i];
				return "";
		
	}
	
    /**
     * 判断List是否为空,非空返回true,空则返回false
     * 
     * @param list
     * @return boolean
     */
    public static boolean isListNotNull(List<?> list) {
        return null != list && list.size() > 0;
    }
    /**
     * 判断是否有数字
     * @param number
     * @return
     */
	public static boolean isNumber(String number){
		int index = number.indexOf(".");
		if(index<0){
			return StringUtils.isNumeric(number);
		}else{
			String num1 = number.substring(0,index);
			String num2 = number.substring(index+1);			
			return StringUtils.isNumeric(num1) && StringUtils.isNumeric(num2);
		}
	}
	
	/**
	 * 生成随机数
	 * @param length
	 * @return
	 */
	public static String RandomStr(int length){
		Random random = new Random();
		StringBuilder sb = new StringBuilder("");
		for(int i = 0; i<length; i++){
			sb.append(String.valueOf(random.nextInt(10)));
		}
		return sb.toString();

	}
	
	/**
	 * 2级xml
	 * @param xml
	 * @return
	 */
	public static Map<String, String> parseXml(String xml){
		Map<String ,String> map = new HashMap<String,String>();
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root  = document.getRootElement();
			List<Element> childList = root.elements();
			if (childList!=null) {
				for (Element element : childList) {
					map.put(element.getName(), element.getStringValue());
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 屏蔽部分字符串
	 * @param name ：字符串
	 * @param startChar : 从第几位开始屏蔽
	 * @return
	 */
	public static String shieldString(String name, int startChar){
		if (StringUtils.isBlank(name)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		if (name.length() > startChar) {
			sb.append(name.substring(0, startChar));
			for (int i = 0; i < name.length() - startChar; i++) {
				sb.append("*");
			} 
		}else{
			sb.append(name.substring(0, name.length()));
		}
		return sb.toString();
	}
		
	//屏蔽身份证后四位
	public static String forbiddenIdCard(String idCard){
		if (StringUtils.isBlank(idCard)) {
			return "";
		}
		if (idCard.length()<15) {
			return "";
		}
		idCard = idCard.substring(0, idCard.length()-4)+"****";
		return idCard;
	}

	
	
	public static String getDecode( String str) {
		if (StringUtils.isNotBlank(str)) 
			return StringEscapeUtils.unescapeHtml4(str);
		return "";
	}
	
	/**
	 * 字符串前面补充0
	 * @param code
	 * @param len
	 * @return
	 */
	 public static String codeAddOne(String code, int len){
	     Integer intHao = Integer.parseInt(code);
	     intHao++;
	     String strHao = intHao.toString();
	     while (strHao.length() < len) {
	         strHao = "0" + strHao;
	     }
	     System.err.println("*********" +strHao);
	     return strHao;
	 }
	
	/**
	 * 转换成2位小数
	 * @param number
	 * @return
	 */
	public static BigDecimal chang2bitNumber(BigDecimal number){
		
		if(number.compareTo(BigDecimal.ZERO)==0){
			return new BigDecimal(changToString("0"));
		}		
		String numberString = String.valueOf(number);		
		numberString = changToString(numberString);
		number = new BigDecimal(numberString);
		
		return number;
	}
	public static String changToString(String number){
		
		if(number.lastIndexOf(".")==-1){
			return number+".00";
		}
		
		return number.substring(0,number.lastIndexOf(".")+3);
	}
	
	/**
	 * 用于去除小数点右边的非0后面为0的数据
	 * 例如： str = “120.00000001000000”，则返回120.00000001
	 * @param str
	 * @return
	 */
	public static String dislodgeZone(String str){
		if(str == null){
			return str;
		}
		if(!str.contains(".")){
			return str;
		}
		while("0".equals(str.substring(str.length() - 1, str.length()))){
			str = str.substring(0, str.length() -1);
		}
		if(".".equals(str.substring(str.length() - 1, str.length()))){
			str = str.substring(0, str.length() -1);
		}
		return str;
	}

	public static String likeParam(String param){
		if(param!=null){
			return "%"+param+"%";
		}
		return null;
	}
	public static void main(String [] args){
		System.out.println(dislodgeZone(null));
	}
}
