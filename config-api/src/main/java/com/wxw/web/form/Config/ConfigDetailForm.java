package com.wxw.web.form.Config;


import com.wxw.model.ConfigDetail;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author:Created by wanxiaowei
 * @Description:
 * @Date:Created in 13:42 2017/8/11
 * @Modified By :
 */
public class ConfigDetailForm extends ConfigDetail {
    @Override
    @NotBlank(message = "配置项明细key不能为空")
    public String getKey() {
        return super.getKey();
    }
}
