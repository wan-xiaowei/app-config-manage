package com.wxw.web.form.LegalOperateLog;

import com.wxw.enums.LogType;
import com.wxw.model.LegalOperateLog;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * @Author:Created by wanxiaowei
 * @Description:
 * @Date:Created in 12:02 2017/7/19
 * @Modified By :
 */
public class LegalOperateLogForm extends LegalOperateLog {
    @Override
    @NotBlank
    public String getDataId() {
        return super.getDataId();
    }

    @Override
    @NotBlank
    public LogType getLogType() {
        return super.getLogType();
    }

    @Override
    @NotBlank
    public String getOperateType() {
        return super.getOperateType();
    }

    @Override
    @NotBlank
    public String getOperateDetails() {
        return super.getOperateDetails();
    }

    @Override
    @NotBlank
    public String getOperateId() {
        return super.getOperateId();
    }

    @Override
    @NotBlank
    public String getOperateUserName() {
        return super.getOperateUserName();
    }

    @Override
    @NotBlank
    public Date getOperateTime() {
        return super.getOperateTime();
    }
}
