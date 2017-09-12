package com.wxw.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:Created by wanxiaowei
 * @Description:
 * @Date:Created in 10:43 2017/8/11
 * @Modified By :
 */
public class ConfigDto implements Serializable {
    private static final long serialVersionUID = 1L;

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
     * 创建人姓名
     */
    private String createUserName;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人姓名
     */
    private String modifyUserName;

    /**
     * 修改时间
     */
    private Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getCommont() {
        return commont;
    }

    public void setCommont(String commont) {
        this.commont = commont;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyUserName() {
        return modifyUserName;
    }

    public void setModifyUserName(String modifyUserName) {
        this.modifyUserName = modifyUserName;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Boolean getIsLink() {
        return isLink;
    }

    public void setIsLink(Boolean link) {
        isLink = link;
    }
}
