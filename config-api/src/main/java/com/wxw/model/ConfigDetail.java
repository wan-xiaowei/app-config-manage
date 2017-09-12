package com.wxw.model;

import java.io.Serializable;
import java.util.Date;

public class ConfigDetail implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 配置项id
     */
    private Integer confId;

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
     * 备注
     */
    private String commont;

    /**
     * 预留列
     */
    private String preColoum;

    /**
     * 创建人id
     */
    private String createUserId;

    /**
     * 创建人姓名
     */
    private String createUserName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人id
     */
    private String modifyUserId;

    /**
     * 修改人姓名
     */
    private String modifyUserName;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 是否删除
     */
    private Boolean isDel;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public ConfigDetail withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConfId() {
        return confId;
    }

    public ConfigDetail withConfId(Integer confId) {
        this.setConfId(confId);
        return this;
    }

    public void setConfId(Integer confId) {
        this.confId = confId;
    }

    public String getCode() {
        return code;
    }

    public ConfigDetail withCode(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getKey() {
        return key;
    }

    public ConfigDetail withKey(String key) {
        this.setKey(key);
        return this;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public ConfigDetail withValue(String value) {
        this.setValue(value);
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getLinkId() {
        return linkId;
    }

    public ConfigDetail withLinkId(Integer linkId) {
        this.setLinkId(linkId);
        return this;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getCommont() {
        return commont;
    }

    public ConfigDetail withCommont(String commont) {
        this.setCommont(commont);
        return this;
    }

    public void setCommont(String commont) {
        this.commont = commont;
    }

    public String getPreColoum() {
        return preColoum;
    }

    public ConfigDetail withPreColoum(String preColoum) {
        this.setPreColoum(preColoum);
        return this;
    }

    public void setPreColoum(String preColoum) {
        this.preColoum = preColoum;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public ConfigDetail withCreateUserId(String createUserId) {
        this.setCreateUserId(createUserId);
        return this;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public ConfigDetail withCreateUserName(String createUserName) {
        this.setCreateUserName(createUserName);
        return this;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ConfigDetail withCreateTime(Date createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyUserId() {
        return modifyUserId;
    }

    public ConfigDetail withModifyUserId(String modifyUserId) {
        this.setModifyUserId(modifyUserId);
        return this;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public String getModifyUserName() {
        return modifyUserName;
    }

    public ConfigDetail withModifyUserName(String modifyUserName) {
        this.setModifyUserName(modifyUserName);
        return this;
    }

    public void setModifyUserName(String modifyUserName) {
        this.modifyUserName = modifyUserName;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public ConfigDetail withModifyTime(Date modifyTime) {
        this.setModifyTime(modifyTime);
        return this;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public ConfigDetail withIsDel(Boolean isDel) {
        this.setIsDel(isDel);
        return this;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", confId=").append(confId);
        sb.append(", code=").append(code);
        sb.append(", key=").append(key);
        sb.append(", value=").append(value);
        sb.append(", linkId=").append(linkId);
        sb.append(", commont=").append(commont);
        sb.append(", preColoum=").append(preColoum);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createUserName=").append(createUserName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyUserId=").append(modifyUserId);
        sb.append(", modifyUserName=").append(modifyUserName);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", isDel=").append(isDel);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}