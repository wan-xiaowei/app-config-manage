package com.wxw.model;

import java.io.Serializable;
import java.util.Date;

public class Config implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 配置编码
     */
    private String code;

    /**
     * 配置项名称
     */
    private String name;

    /**
     * 是否联动
     */
    private Boolean isLink;

    /**
     * 使用系统
     */
    private String system;

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

    public Config withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public Config withCode(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public Config withName(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsLink() {
        return isLink;
    }

    public Config withIsLink(Boolean isLink) {
        this.setIsLink(isLink);
        return this;
    }

    public void setIsLink(Boolean isLink) {
        this.isLink = isLink;
    }

    public String getSystem() {
        return system;
    }

    public Config withSystem(String system) {
        this.setSystem(system);
        return this;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getCommont() {
        return commont;
    }

    public Config withCommont(String commont) {
        this.setCommont(commont);
        return this;
    }

    public void setCommont(String commont) {
        this.commont = commont;
    }

    public String getPreColoum() {
        return preColoum;
    }

    public Config withPreColoum(String preColoum) {
        this.setPreColoum(preColoum);
        return this;
    }

    public void setPreColoum(String preColoum) {
        this.preColoum = preColoum;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public Config withCreateUserId(String createUserId) {
        this.setCreateUserId(createUserId);
        return this;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public Config withCreateUserName(String createUserName) {
        this.setCreateUserName(createUserName);
        return this;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Config withCreateTime(Date createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyUserId() {
        return modifyUserId;
    }

    public Config withModifyUserId(String modifyUserId) {
        this.setModifyUserId(modifyUserId);
        return this;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public String getModifyUserName() {
        return modifyUserName;
    }

    public Config withModifyUserName(String modifyUserName) {
        this.setModifyUserName(modifyUserName);
        return this;
    }

    public void setModifyUserName(String modifyUserName) {
        this.modifyUserName = modifyUserName;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public Config withModifyTime(Date modifyTime) {
        this.setModifyTime(modifyTime);
        return this;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public Config withIsDel(Boolean isDel) {
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
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", isLink=").append(isLink);
        sb.append(", system=").append(system);
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