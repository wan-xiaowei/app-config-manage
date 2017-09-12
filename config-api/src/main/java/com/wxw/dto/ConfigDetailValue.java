package com.wxw.dto;

import java.io.Serializable;

/**
 * @Author:Created by wanxiaowei
 * @Description:
 * @Date:Created in 2:11 2017/8/13
 * @Modified By :
 */
public class ConfigDetailValue implements Serializable{
    private int id;
    private String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
