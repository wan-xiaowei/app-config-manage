package com.wxw.web.form.LegalOperateLog;


import com.wxw.enums.LogType;
import com.wxw.web.form.PageSearchForm;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @Author:Created by wanxiaowei
 * @Description:
 * @Date:Created in 10:54 2017/7/24
 * @Modified By :
 */
public class LegalOperateLogPageSearchForm implements PageSearchForm,Serializable {
    private static final long serialVersionUID = 1L;

    private String dataId;

    private LogType logType;


    private Integer pageIndex;


    private Integer pageSize;

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

    @Override
    public Integer getPageIndex() {
        return pageIndex;
    }

    @Override
    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    @Override
    public Integer getPageSize() {
        return pageSize;
    }

    @Override
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
