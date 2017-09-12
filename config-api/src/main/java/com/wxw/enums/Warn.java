package com.wxw.enums;

/**
 * @Author:Created by wanxiaowei
 * @Description:
 * @Date:Created in 15:05 2017/8/1
 * @Modified By :
 */
public enum Warn implements DisplayedEnum<Warn> {
    LOGWARN("1","日志服务异常"),

    SERVERWARN("2","服务异常");

    private String value;

    private String label;

    private Warn(String value, String label) {
        this.value = value;
        this.label = label;
    }

    @Override
    public String toString() {
        return display();
    }
}
