package com.wxw.dto;

import java.io.Serializable;

/**
 * @Author:Created by wanxiaowei
 * @Description:
 * @Date:Created in 1:36 2017/8/13
 * @Modified By :
 */
public class ConfigNameDto implements Serializable{
    private Integer id;
    private String confName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConfName() {
        return confName;
    }

    public void setConfName(String confName) {
        this.confName = confName;
    }
}
