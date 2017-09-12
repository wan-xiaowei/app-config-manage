package com.wxw.exception;

/**
 * @Author:Created by wanxiaowei
 * @Description: 异常类
 * @Date:Created in 9:09 2017/7/3
 */
public class ParameterIncorrectException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    /**
     * 错误编码
     */
    private String errorCode;
    /**
     * 消息是否为属性文件中的Key
     */
    private boolean propertiesKey = true;

    /**
     * @param msg 信息描述
     */
    public ParameterIncorrectException(String msg) {
        super(msg);
    }

    /**
     * @param errorCode 错误编码
     * @param msg       信息描述
     */
    public ParameterIncorrectException(String errorCode, String msg) {
        this(errorCode, msg, true);
    }

    /**
     * @param errorCode 错误编码
     * @param msg       信息描述
     */
    public ParameterIncorrectException(String errorCode, String msg, Throwable cause) {
        this(errorCode, msg, cause, true);
    }

    /**
     * @param errorCode     错误编码
     * @param message       信息描述
     * @param propertiesKey 消息是否为属性文件中的Key
     */
    public ParameterIncorrectException(String errorCode, String message, boolean propertiesKey) {
        super(message);
        this.setErrorCode(errorCode);
        this.setPropertiesKey(propertiesKey);
    }

    /**
     * @param errorCode 错误编码
     * @param message   信息描述
     */
    public ParameterIncorrectException(String errorCode, String message, Throwable cause, boolean propertiesKey) {
        super(message, cause);
        this.setErrorCode(errorCode);
        this.setPropertiesKey(propertiesKey);
    }

    /**
     * @param message 信息描述
     * @param cause   根异常类（可以存入任何异常）
     */
    public ParameterIncorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isPropertiesKey() {
        return propertiesKey;
    }

    public void setPropertiesKey(boolean propertiesKey) {
        this.propertiesKey = propertiesKey;
    }

}


