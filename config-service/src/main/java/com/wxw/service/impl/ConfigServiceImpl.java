package com.wxw.service.impl;


import com.wxw.bean.Page;
import com.wxw.bean.SysUserInfo;
import com.wxw.dto.ConfigDetailDto;
import com.wxw.dto.ConfigDetailValue;
import com.wxw.dto.ConfigDto;
import com.wxw.dto.ConfigNameDto;
import com.wxw.mapper.ConfigDetailMapper;
import com.wxw.mapper.ConfigMapper;
import com.wxw.model.Config;
import com.wxw.model.ConfigDetail;
import com.wxw.model.ConfigDetailExample;
import com.wxw.model.ConfigExample;
import com.wxw.service.ConfigService;
import com.wxw.util.CommonUtils;
import com.wxw.util.HttpClientTools;
import com.wxw.web.form.Config.ConfigDetailForm;
import com.wxw.web.form.Config.ConfigForm;
import com.wxw.web.form.Config.ConfigPageSearchForm;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author:Created by wanxiaowei
 * @Description:
 * @Date:Created in 11:19 2017/8/11
 * @Modified By :
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private ConfigMapper configMapper;

    @Autowired
    private ConfigDetailMapper configDetailMapper;

    @Override
    public Page<ConfigDto> loadConfigByPage(ConfigPageSearchForm configPageSearchForm) {
        ConfigExample configExample = new ConfigExample();
        ConfigExample.Criteria criteria=configExample.createCriteria();
        if(StringUtils.isNotEmpty(configPageSearchForm.getCreateUserName()))
            criteria.andCreateUserNameLike(CommonUtils.likeParam(configPageSearchForm.getCreateUserName()));
        if(StringUtils.isNotEmpty(configPageSearchForm.getCode()))
            criteria.andCodeLike(CommonUtils.likeParam(configPageSearchForm.getCode()));
        if(StringUtils.isNotEmpty(configPageSearchForm.getName()))
            criteria.andNameLike(CommonUtils.likeParam(configPageSearchForm.getName()));
        criteria.andIsDelEqualTo(false);
        int startIndex = (configPageSearchForm.getPageIndex() - 1) * configPageSearchForm.getPageSize();
        RowBounds rowBounds = new RowBounds(startIndex, configPageSearchForm.getPageSize());
        List<Config> configList = configMapper.selectByExampleWithRowbounds(configExample, rowBounds);
        long total = configMapper.countByExample(configExample);
        List<ConfigDto> configDtoList= configList.stream().map(config -> {
            ConfigDto configDto = new ConfigDto();
            BeanUtils.copyProperties(config , configDto);
            return configDto;
        }).collect(Collectors.toList());
        Page<ConfigDto> page = new Page<>(configPageSearchForm.getPageIndex(), configPageSearchForm.getPageSize(), total, configDtoList);
        return page;
    }

    @Override
    public Optional<String> add(SysUserInfo sysUserInfo, ConfigForm configForm) {
        configForm.setIsDel(false);
        configForm.setCreateTime(new Date());
        configForm.setCreateUserId(sysUserInfo.getUserId());
        configForm.setCreateUserName(sysUserInfo.getUserName());
        int id = configMapper.insert(configForm);

        String[] ConfigDetailFormArray = configForm.getConfigDetailFormList().split(";");
        List<ConfigDetailForm> configDetailList = Arrays.stream(ConfigDetailFormArray).map(configDetailForm->{
            JSONObject jsonobject = JSONObject.fromObject(configDetailForm);
            return (ConfigDetailForm) JSONObject.toBean(jsonobject,ConfigDetailForm.class);
        }).collect(Collectors.toList());
        List<ConfigDetailForm> configDetails = configDetailList.stream().map(configDetailForm -> {
            configDetailForm.setCreateUserId(sysUserInfo.getUserId());
            configDetailForm.setCreateUserName(sysUserInfo.getUserName());
            configDetailForm.setCreateTime(new Date());
            configDetailForm.setIsDel(false);
            configDetailForm.setConfId(id);
            configDetailForm.setCode(configForm.getCode());
            return configDetailForm;
        }).collect(Collectors.toList());
        for(ConfigDetailForm configDetailForm : configDetails){
            configDetailMapper.insert(configDetailForm);
        }

        return null;
    }

    @Override
    public Optional<String> updateConfig(ConfigForm form, SysUserInfo sysUserInfo) {
        form.setModifyUserId(sysUserInfo.getUserId());
        form.setModifyUserName(sysUserInfo.getUserName());
        form.setModifyTime(new Date());
        configMapper.updateByPrimaryKeySelective(form);

        ConfigDetailExample configDetailExample = new ConfigDetailExample();
        configDetailExample.createCriteria().andConfIdEqualTo(form.getId()).andIsDelEqualTo(false);
        ConfigDetail configDetail = new ConfigDetail();
        configDetail.setIsDel(true);
        configDetail.setModifyUserId(sysUserInfo.getUserId());
        configDetail.setModifyUserName(sysUserInfo.getUserName());
        configDetail.setModifyTime(new Date());
        configDetailMapper.updateByExampleSelective(configDetail,configDetailExample);

        String[] ConfigDetailFormArray = form.getConfigDetailFormList().split(";");

        List<ConfigDetailForm> configDetailList = Arrays.stream(ConfigDetailFormArray).map(configDetailForm->{
            JSONObject jsonobject = JSONObject.fromObject(configDetailForm);
            return (ConfigDetailForm) JSONObject.toBean(jsonobject,ConfigDetailForm.class);
        }).collect(Collectors.toList());
        List<ConfigDetailForm> configDetails = configDetailList.stream().map(configDetailForm -> {
            configDetailForm.setModifyUserId(sysUserInfo.getUserId());
            configDetailForm.setModifyUserName(sysUserInfo.getUserName());
            configDetailForm.setModifyTime(new Date());
            configDetailForm.setIsDel(true);
            configDetailForm.setConfId(form.getId());
            return configDetailForm;
        }).collect(Collectors.toList());
        for(ConfigDetailForm configDetailForm : configDetails){
            configDetailMapper.insert(configDetailForm);
        }
        return null;
    }

    @Override
    public Optional<String> deleteConfigByIds(SysUserInfo sysUserInfo, String ids) {
        List<Integer> idList = Arrays.stream(ids.split(",")).map(id->Integer.parseInt(id)).collect(Collectors.toList());
        ConfigExample configExample = new ConfigExample();
        configExample.createCriteria().andIsDelEqualTo(false).andIdIn(idList);
        Config config = new Config();
        config.setIsDel(true);
        config.setModifyUserId(sysUserInfo.getUserId());
        config.setModifyUserName(sysUserInfo.getUserName());
        config.setModifyTime(new Date());
        configMapper.updateByExampleSelective(config,configExample);

        ConfigDetailExample configDetailExample = new ConfigDetailExample();
        configDetailExample.createCriteria().andIsDelEqualTo(false).andConfIdIn(idList);
        ConfigDetail configDetail = new ConfigDetail();
        configDetail.setIsDel(true);
        configDetail.setModifyUserId(sysUserInfo.getUserId());
        configDetail.setModifyUserName(sysUserInfo.getUserName());
        configDetail.setModifyTime(new Date());
        configDetailMapper.updateByExampleSelective(configDetail,configDetailExample);

        return null;
    }

    @Override
    public List<Config> getConfigByIds(String ids) {
        return null;
    }

    @Override
    public Page<ConfigDetailDto> getConfigDetailById(ConfigPageSearchForm configPageSearchForm) {
        Assert.notNull(configPageSearchForm.getId() , "配置项id不能为空");
        int startIndex = (configPageSearchForm.getPageIndex() - 1) * configPageSearchForm.getPageSize();
        RowBounds rowBounds = new RowBounds(startIndex, configPageSearchForm.getPageSize());
        ConfigDetailExample configDetailExample = new ConfigDetailExample();
        ConfigDetailExample.Criteria criteria=configDetailExample.createCriteria();
        criteria.andIsDelEqualTo(false);
        criteria.andConfIdEqualTo(configPageSearchForm.getId());
        List<ConfigDetail> configDetailList = configDetailMapper.selectByExampleWithRowbounds(configDetailExample,rowBounds);
        long total = configDetailMapper.countByExample(configDetailExample);
        List<ConfigDetailDto> list = configDetailList.stream().map(configDetail -> {
            ConfigDetailDto configDetailDto = new ConfigDetailDto();
            if(configDetail.getLinkId()!=null){
            ConfigExample example = new ConfigExample();
            ConfigExample.Criteria criteria1 = example.createCriteria();
           /* if(configDetail.getLinkId()!=null){
                criteria1.andIdEqualTo(configDetail.getLinkId());
            }*/
            criteria1.andIdEqualTo(configDetail.getConfId());
            criteria1.andIsDelEqualTo(false);
            Config conf = configMapper.selectByExample(example).get(0);
            configDetailDto.setConfName(conf.getSystem()+"-"+conf.getName());

                ConfigDetail configDetail1 = configDetailMapper.selectByPrimaryKey(configDetail.getLinkId());
                configDetailDto.setLinkValue(configDetail1.getValue());
            }
            BeanUtils.copyProperties(configDetail,configDetailDto);
            return  configDetailDto;
        }).collect(Collectors.toList());

        Page<ConfigDetailDto> page = new Page<>(configPageSearchForm.getPageIndex(), configPageSearchForm.getPageSize(), total, list);
        return page;
    }

    @Override
    public ConfigDto getConfigById(Integer id) {
        ConfigDto configDto = new ConfigDto();
        ConfigExample configExample = new ConfigExample();
        configExample.createCriteria().andIsDelEqualTo(false);
        Config config = configMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(config,configDto);
        return configDto;
    }



    @Override
    public List<ConfigDto> loadConfig(ConfigPageSearchForm form) {
        return null;
    }

    @Override
    public Page<ConfigNameDto> loadConfigName(int pageIndex, int pageSize) {

        int startIndex = (pageIndex - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(startIndex,pageSize);
        List<ConfigNameDto> list = configMapper.selectConfigNameWithRowBounds(rowBounds);

        ConfigExample configExample = new ConfigExample();
        configExample.createCriteria().andIsDelEqualTo(false);
        long total = configMapper.countByExample(configExample);
        Page<ConfigNameDto> page = new Page<>(pageIndex,pageSize,total,list);
        return page;
    }

    @Override
    public Page<ConfigDetailValue> loadConfigDetailValue(ConfigPageSearchForm configPageSearchForm) {
        Assert.notNull(configPageSearchForm.getId() , "配置项id不能为空");
        int startIndex = (configPageSearchForm.getPageIndex() - 1) * configPageSearchForm.getPageSize();
        RowBounds rowBounds = new RowBounds(startIndex, configPageSearchForm.getPageSize());
        ConfigDetailExample configDetailExample = new ConfigDetailExample();
        configDetailExample.createCriteria().andIsDelEqualTo(false).andConfIdEqualTo(configPageSearchForm.getId());
        List<ConfigDetail> configDetailList=configDetailMapper.selectByExampleWithRowbounds(configDetailExample,rowBounds);
        long total = configDetailMapper.countByExample(configDetailExample);
        List<ConfigDetailValue> configDetailValueList = configDetailList.stream().map(configDetail -> {
            ConfigDetailValue configDetailValue = new ConfigDetailValue();
            BeanUtils.copyProperties(configDetail,configDetailValue);
            return configDetailValue;
        }).collect(Collectors.toList());
        Page<ConfigDetailValue> page = new Page<>(configPageSearchForm.getPageIndex(),configPageSearchForm.getPageSize(),total,configDetailValueList);
        return page;
    }

    @Override
    public void test(String id) {
        Map map = new HashMap<String,Object>();
        try {
            HttpClientTools.PostRequst(map,"http://localhost:8080/brand-sale/Receipt/add");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        SysUserInfo sysUserInfo = new SysUserInfo();
        sysUserInfo.setUserId("11");
        sysUserInfo.setUserName("11");
        deleteConfigByIds(sysUserInfo,id);
    }
}
