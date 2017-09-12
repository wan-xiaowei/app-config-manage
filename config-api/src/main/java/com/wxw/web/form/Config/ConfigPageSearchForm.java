package com.wxw.web.form.Config;



import com.wxw.web.form.PageSearchForm;

import java.io.Serializable;


/**
 * @Author:Created by wanxiaowei
 * @Description: Vat税收管理查询操作请求参数封装对象
 * @Date:Created in 17:52 2017/7/18
 * @Modified By :
 */
public class ConfigPageSearchForm implements PageSearchForm,Serializable{

    private static final long serialVersionUID = 1L;
    /**
     * 配置项id
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
     * 添加人
     */
    private String createUserName;

    private Integer pageIndex;


    private Integer pageSize;

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

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
