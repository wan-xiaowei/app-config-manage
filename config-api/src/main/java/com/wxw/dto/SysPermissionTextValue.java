package com.wxw.dto;

import java.io.Serializable;

/**
 * @Author:Created by wanxiaowei
 * @Description:
 * @Date:Created in 15:15 2017/9/12
 * @Modified By :
 */
public class SysPermissionTextValue implements Serializable {
    private static final long serialVersionUID = -7298248946100882551L;
    private String text;
    private String value;

    public SysPermissionTextValue() {
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
