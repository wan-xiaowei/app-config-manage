package com.wxw.web.form.Config;


import com.wxw.model.Config;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * @Author:Created by wanxiaowei
 * @Description: Vat税收管理添加操作请求参数封装对象
 * @Date:Created in 19:10 2017/7/18
 * @Modified By :
 */
public class ConfigForm extends Config {
    /*@Valid
    @NotEmpty(message = "配置项明细不能全为空")
    public List<ConfigDetailForm> configDetailFormList;*/


    @NotEmpty(message = "配置项明细不能全为空")
    public String configDetailFormList;

    @Override
    @NotBlank(message = "配置编码不能为空")
    public String getCode() {
        return super.getCode();
    }

    @Override
    @NotBlank(message = "配置项名称不能为空")
    public String getName() {
        return super.getName();
    }

    @Override
    @NotBlank(message = "使用系统不能为空")
    public String getSystem() {
        return super.getSystem();
    }

    @Override
    @NotBlank(message = "备注不能为空")
    public String getCommont() {
        return super.getCommont();
    }

    @Override
    @NotBlank(message = "预留列不能为空")
    public String getPreColoum() {
        return super.getPreColoum();
    }

  /*  public List<ConfigDetailForm> getConfigDetailFormList() {
        return configDetailFormList;
    }

    public void setConfigDetailFormList(List<ConfigDetailForm> configDetailFormList) {
        this.configDetailFormList = configDetailFormList;
    }*/

    public String getConfigDetailFormList() {
        return configDetailFormList;
    }

    public void setConfigDetailFormList(String configDetailFormList) {
        this.configDetailFormList = configDetailFormList;
    }
}
