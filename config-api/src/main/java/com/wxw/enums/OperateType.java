package com.wxw.enums;

/**
 * @Author:Created by wanxiaowei
 * @Description:
 * @Date:Created in 15:16 2017/8/1
 * @Modified By :
 */
public enum OperateType implements DisplayedEnum<OperateType> {

    ADD("add","添加"),

    UPDATE("update","编辑"),

    DELETE("delete","删除"),

    AUDIT("audit","审核");

    private String value;

    private String label;

    private OperateType(String value, String label) {
        this.value = value;
        this.label = label;
    }

    @Override
    public String toString() {
        return display();
    }
}
