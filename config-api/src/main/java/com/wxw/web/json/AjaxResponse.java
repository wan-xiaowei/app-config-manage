package com.wxw.web.json;


import java.io.Serializable;

public class AjaxResponse<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean success;
    private String message;
    private T data;
    private boolean hasPermission = Boolean.TRUE;
    private String code;
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isHasPermission() {
        return hasPermission;
    }

    public void setHasPermission(boolean hasPermission) {
        this.hasPermission = hasPermission;
    }

    public String getCode() {return code;}

    public void setCode(String code) {this.code = code;}
}
