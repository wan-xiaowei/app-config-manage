package com.wxw.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author:Created by wanxiaowei
 * @Description:
 * @Date:Created in 20:34 2017/6/15
 * @Modified By :
 */
public class UriReplaceUtil {
    public  static  final Logger logger = LoggerFactory.getLogger(UriReplaceUtil.class);

    private static  final String suffix = "Controller";

    private UriReplaceUtil(){

    }

    public static String replaceUri(String uri) {
        //形式: "/internal-transfer/cashierPayment/index","/internal-transfer/cashierPayment";
        try {
            int thirdIndex = 0 ;
            StringBuilder sb = new StringBuilder();
            String newUri = uri.replaceAll("/", ".").replaceFirst(".", "");
            int count = StringUtils.countMatches(newUri,".");
            int firstIndex = newUri.indexOf('.');
            int secondIndex = newUri.indexOf('.', firstIndex+1);
            if(count>2)
                thirdIndex= newUri.indexOf('.',secondIndex+1);
            //项目名
            sb.append(newUri.substring(0, firstIndex + 1));
            if (firstIndex < secondIndex) {
                String controllerString;
                //类名
                String temp = newUri.substring(firstIndex + 1, secondIndex);
                char oldFirstChar = temp.charAt(0);
                char newFirstChar = (Character.toString(oldFirstChar)).toUpperCase().charAt(0);
                //将"-"+小写字母转换为大写字母
                int index = temp.indexOf('-');
                if(index > -1){
                    char oldChar = temp.charAt(index+1);
                    char newChar = (Character.toString(oldChar)).toUpperCase().charAt(0);
                    controllerString = temp.replaceFirst(Character.toString(oldFirstChar), Character.toString(newFirstChar)).replaceFirst("-"+oldChar,Character.toString(newChar)) + suffix;
                }else {
                    controllerString = temp.replaceFirst(Character.toString(oldFirstChar), Character.toString(newFirstChar))+ suffix;
                }
                sb.append(controllerString);
                //方法名后
                if(count> 2){
                    sb.append(newUri.substring(secondIndex, thirdIndex));
                }else {
                    sb.append(newUri.substring(secondIndex, newUri.length()));
                }

               logger.info("new uri {}",newUri);
                return sb.toString();
            } else {
                String temp = newUri.substring(firstIndex + 1, newUri.length());
                char oldChar = temp.charAt(0);
                char newChar = (Character.toString(oldChar)).toUpperCase().charAt(0);
                String controllerString = temp.replaceFirst(Character.toString(oldChar), Character.toString(newChar)) + suffix;
                sb.append(controllerString);
                return sb.toString();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
