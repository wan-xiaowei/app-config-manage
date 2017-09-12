package com.wxw.bean;

import java.io.Serializable;

/**
 * @Author:Created by wanxiaowei
 * @Description:
 * @Date:Created in 14:59 2017/9/12
 * @Modified By :
 */

public class SysUserInfo implements Serializable {
    private static final long serialVersionUID = -7938218930998963495L;
    private String userId;
    private String userName;
    private String departmentId;
    private String departmentName;
    private String email;
    private String employeeName;
    private Object leader;
    private String oaId;

    public SysUserInfo() {
    }

    public String getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployeeName() {
        return this.employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Object getLeader() {
        return this.leader;
    }

    public void setLeader(Object leader) {
        this.leader = leader;
    }

    public String getOaId() {
        return this.oaId;
    }

    public void setOaId(String oaId) {
        this.oaId = oaId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
