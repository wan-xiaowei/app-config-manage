package com.wxw.model;



import com.wxw.enums.LogType;

import java.io.Serializable;
import java.util.Date;

public class LegalOperateLog implements Serializable {
    /**
     * 
     */
    private String id;

    /**
     * 业务数据ID
     */
    private String dataId;

    /**
     * 日志类型（帐号管理日志、协议管理日志、店铺管理日志、公司管理日志、VAT税收管理）
     */
    private LogType logType;

    /**
     * 操作类型（驳回,新增,编辑,审核）
     */
    private String operateType;

    /**
     * 操作详细日志
     */
    private String operateDetails;

    /**
     * 操作用户
     */
    private String operateId;

    /**
     * 操作用户姓名
     */
    private String operateUserName;

    /**
     * 操作时间
     */
    private Date operateTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public LegalOperateLog withId(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataId() {
        return dataId;
    }

    public LegalOperateLog withDataId(String dataId) {
        this.setDataId(dataId);
        return this;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public LogType getLogType() {
        return logType;
    }

    public LegalOperateLog withLogType(LogType logType) {
        this.setLogType(logType);
        return this;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }

    public String getOperateType() {
        return operateType;
    }

    public LegalOperateLog withOperateType(String operateType) {
        this.setOperateType(operateType);
        return this;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOperateDetails() {
        return operateDetails;
    }

    public LegalOperateLog withOperateDetails(String operateDetails) {
        this.setOperateDetails(operateDetails);
        return this;
    }

    public void setOperateDetails(String operateDetails) {
        this.operateDetails = operateDetails;
    }

    public String getOperateId() {
        return operateId;
    }

    public LegalOperateLog withOperateId(String operateId) {
        this.setOperateId(operateId);
        return this;
    }

    public void setOperateId(String operateId) {
        this.operateId = operateId;
    }

    public String getOperateUserName() {
        return operateUserName;
    }

    public LegalOperateLog withOperateUserName(String operateUserName) {
        this.setOperateUserName(operateUserName);
        return this;
    }

    public void setOperateUserName(String operateUserName) {
        this.operateUserName = operateUserName;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public LegalOperateLog withOperateTime(Date operateTime) {
        this.setOperateTime(operateTime);
        return this;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dataId=").append(dataId);
        sb.append(", logType=").append(logType);
        sb.append(", operateType=").append(operateType);
        sb.append(", operateDetails=").append(operateDetails);
        sb.append(", operateId=").append(operateId);
        sb.append(", operateUserName=").append(operateUserName);
        sb.append(", operateTime=").append(operateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}