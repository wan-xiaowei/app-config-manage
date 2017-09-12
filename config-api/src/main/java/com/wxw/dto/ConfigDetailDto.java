package com.wxw.dto;

import java.io.Serializable;

/**
 * @Author:Created by wanxiaowei
 * @Description:
 * @Date:Created in 17:39 2017/8/11
 * @Modified By :
 */
public class ConfigDetailDto implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private Integer id;

    /**
     * 配置项id
     */
    private Integer confId;

    /**
     * 配置项名称
     */
    private String confName;

    /**
     * 配置项编码
     */
    private String code;

    /**
     *
     */
    private String key;

    /**
     *
     */
    private String value;

    /**
     * 本表的联动id
     */
    private Integer linkId;
    /**
     * 本表的联动value值
     */
    private String linkValue;
    /**
     * 备注
     */
    private String commont;

    /**
     * 预留列
     */
    private String preColoum;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConfId() {
        return confId;
    }

    public void setConfId(Integer confId) {
        this.confId = confId;
    }

    public String getConfName() {
        return confName;
    }

    public void setConfName(String confName) {
        this.confName = confName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getCommont() {
        return commont;
    }

    public void setCommont(String commont) {
        this.commont = commont;
    }

    public String getPreColoum() {
        return preColoum;
    }

    public void setPreColoum(String preColoum) {
        this.preColoum = preColoum;
    }

    public String getLinkValue() {
        return linkValue;
    }

    public void setLinkValue(String linkValue) {
        this.linkValue = linkValue;
    }
}
