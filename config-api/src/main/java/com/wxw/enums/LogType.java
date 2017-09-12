package com.wxw.enums;

/**
 * @Author:Created by wanxiaowei
 * @Description:  日志枚举类型(label值取serviceImpl类的前面部分，区分大小写)
 * @Date:Created in 19:31 2017/7/19
 * @Modified By :
 */
public enum LogType implements DisplayedEnum<LogType> {
    /**
     *账号类型
     */
    ACCOUNTTYPE("1","Account"),
    /**
     *协议类型
     */
    PROTOCOLTYPE("2","LegalProtocol"),
    /**
     *店铺类型
     */
    SHOPTYPE("3","Shop"),
    /**
     *公司类型
     */
    COMPANYTYPE("4","Company"),
    /**
     *VAT税收类型
     */
    VATTYPE("5","VatTax");

    private String value;

    private String label;

    private LogType(String value, String label) {
        this.value = value;
        this.label = label;
    }

    @Override
    public String toString() {
        return display();
    }
}
