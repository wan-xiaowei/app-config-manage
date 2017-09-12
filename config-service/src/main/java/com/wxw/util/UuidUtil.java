package com.wxw.util;

import java.util.UUID;

/**
 * Created by Administrator on 2017/6/23.
 */
public class UuidUtil {
    private UuidUtil(){

    }
    public static String getUuid() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        uuidStr = uuidStr.replace("-", "");
        return uuidStr;
    }
}
