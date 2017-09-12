package com.wxw.util;

import com.alibaba.fastjson.JSON;

import java.io.*;


/**
 * @Author:Created by wanxiaowei
 * @Description:
 * @Date:Created in 15:53 2017/6/29
 * @Modified By :
 */
public class SerUtil {
    public static byte[] serialize(Object object) {
        String jsonString = JSON.toJSONString(object);
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(jsonString);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {

        } finally {
            try {
                if (oos != null)
                    oos.close();
                if (baos != null)
                    baos.close();
            } catch (IOException e) {

            }
        }
        return null;
    }

        public static String unserialize ( byte[] bytes){
            ByteArrayInputStream bais = null;
            try {
                // 反序列化
                bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                return ois.readObject().toString();
            } catch (Exception e) {

            }
            return null;
        }

        public static String unserialize (String key){
            return unserialize(key.getBytes());
        }

    }
