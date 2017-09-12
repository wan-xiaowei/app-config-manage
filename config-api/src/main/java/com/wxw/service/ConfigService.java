package com.wxw.service;



import com.wxw.bean.Page;
import com.wxw.bean.SysUserInfo;
import com.wxw.dto.ConfigDetailDto;
import com.wxw.dto.ConfigDetailValue;
import com.wxw.dto.ConfigDto;
import com.wxw.dto.ConfigNameDto;
import com.wxw.model.Config;
import com.wxw.web.form.Config.ConfigForm;
import com.wxw.web.form.Config.ConfigPageSearchForm;

import java.util.List;
import java.util.Optional;

/**
 * @Author:Created by wanxiaowei
 * @Description:
 * @Date:Created in 18:05 2017/7/18
 * @Modified By :
 */
public interface ConfigService {

    Page<ConfigDto> loadConfigByPage(ConfigPageSearchForm configPageSearchForm);

    Optional<String> add(SysUserInfo sysUserInfo, ConfigForm vatTaxForm);

    Optional<String> updateConfig(ConfigForm form, SysUserInfo sysUserInfo);

    Optional<String> deleteConfigByIds(SysUserInfo sysUserInfo, String ids);

    List<Config> getConfigByIds(String ids);

    /**
     * 根据配置项查询明细列表
     * @param configPageSearchForm
     * @return
     */
    Page<ConfigDetailDto> getConfigDetailById(ConfigPageSearchForm configPageSearchForm);

    ConfigDto getConfigById(Integer id);

    List<ConfigDto> loadConfig(ConfigPageSearchForm form);

    Page<ConfigNameDto> loadConfigName(int pageIndex, int pageSize);

    Page<ConfigDetailValue> loadConfigDetailValue(ConfigPageSearchForm configPageSearchForm);

    void test(String id);
}
