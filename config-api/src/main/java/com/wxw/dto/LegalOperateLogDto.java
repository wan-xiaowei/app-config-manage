package com.wxw.dto;



import com.wxw.enums.LogType;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:Created by wanxiaowei
 * @Description:
 * @Date:Created in 12:45 2017/7/24
 * @Modified By :
 */
public class LegalOperateLogDto implements Serializable {

    private static final long serialVersionUID = 1L;
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

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public LogType getLogType() {
        return logType;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOperateDetails() {
        return operateDetails;
    }

    public void setOperateDetails(String operateDetails) {
        this.operateDetails = operateDetails;
    }

    public String getOperateUserName() {
        return operateUserName;
    }

    public void setOperateUserName(String operateUserName) {
        this.operateUserName = operateUserName;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperateId() {
        return operateId;
    }

    public void setOperateId(String operateId) {
        this.operateId = operateId;
    }
}
