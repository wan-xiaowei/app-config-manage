package com.wxw.admin.controller;

import com.wxw.bean.Page;
import com.wxw.bean.SysUserInfo;
import com.wxw.dto.ConfigDetailDto;
import com.wxw.dto.ConfigDetailValue;
import com.wxw.dto.ConfigDto;
import com.wxw.dto.ConfigNameDto;
import com.wxw.service.ConfigService;
import com.wxw.util.ControllerUtils;
import com.wxw.util.UserInfoUtil;
import com.wxw.web.form.Config.ConfigForm;
import com.wxw.web.form.Config.ConfigPageSearchForm;
import com.wxw.web.json.AjaxResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: Created by wanxiaowei
 * @Description:
 * @Date: Created in 10:39 2017/8/11
 * @Modified By :
 */
@RestController
@Api(value = "配置项", description = "配置项API")
@RequestMapping(value = "/config")
public class ConfigController  {
    public static final Logger logger = LoggerFactory.getLogger(ConfigController.class);


    @Autowired
    private ConfigService configService;

    @GetMapping(value = "/load")
    @ApiOperation(value = "配置项-列表查询", notes = "")
    @ApiImplicitParams({
            // 必须条件
            @ApiImplicitParam(name = "pageIndex", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示记录数", required = true, paramType = "query", dataType = "int"),
            // 可选条件
            @ApiImplicitParam(name = "code", value = "配置编码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "配置项名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "createUserName", value = "添加人", required = false, paramType = "query", dataType = "String")
    })
    public AjaxResponse load(@Valid ConfigPageSearchForm form, BindingResult result, HttpServletRequest request) {
        logger.info("开始 获取配置项列表数据");
        AjaxResponse ajaxResponse = new AjaxResponse();

        if (result.hasErrors()) {
            ControllerUtils.validModelForAjaxResponse(result, ajaxResponse);
            logger.error("获取配置项列表数据，请求参数不正确  {}", ajaxResponse.getMessage());
            return ajaxResponse;
        }
        Page<ConfigDto> page = configService.loadConfigByPage(form);
        ajaxResponse.setSuccess(Boolean.TRUE);
        ajaxResponse.setMessage("获取配置项列表数据成功");
        ajaxResponse.setData(page);
        logger.info("结束 配置项列表数据");
        return ajaxResponse;
    }


    @GetMapping(value = "/loadConfigInfo/{id}")
    @ApiOperation(value = "配置项-查看详情(配置项部分)", notes = "")
    @ApiImplicitParams({
            // 必须条件
            @ApiImplicitParam(name = "id", value = "配置项id", required = true, paramType = "path", dataType = "int")
    })
    public AjaxResponse loadConfigInfo(@PathVariable("id") Integer id, HttpServletRequest request) {
        logger.info("开始 配置项-查看详情(配置项部分)");

        String userId = UserInfoUtil.getUserId(request, null);//当前登录用户ID
        //String userName = UserInfoUtil.getUserInfo(userId).getUserName();//当前登录用户姓名
        AjaxResponse ajaxResponse = new AjaxResponse();
        ConfigDto configDto = configService.getConfigById(id);
        ajaxResponse.setData(configDto);
        ajaxResponse.setSuccess(Boolean.TRUE);
        ajaxResponse.setMessage("配置项-查看详情(配置项部分)成功");
        logger.info("结束 配置项-查看详情(配置项部分)");
        return ajaxResponse;
    }

    @GetMapping(value = "/loadConfigDetailInfo")
    @ApiOperation(value = "配置项-查看详情(配置项明细部分)", notes = "")
    @ApiImplicitParams({
            // 必须条件
            @ApiImplicitParam(name = "pageIndex", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示记录数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "id", value = "配置项id", required = true, paramType = "query", dataType = "int"),
    })
    public AjaxResponse loadConfigDeatilInfo(@Valid ConfigPageSearchForm configPageSearchForm, HttpServletRequest request) {
        logger.info("开始 配置项-查看详情(配置项明细部分)");
        String userId = UserInfoUtil.getUserId(request, null);//当前登录用户ID
        //String userName = UserInfoUtil.getUserInfo(userId).getUserName();//当前登录用户姓名
        AjaxResponse ajaxResponse = new AjaxResponse();
        Page<ConfigDetailDto> page = configService.getConfigDetailById(configPageSearchForm);
        ajaxResponse.setData(page);
        ajaxResponse.setSuccess(Boolean.TRUE);
        ajaxResponse.setMessage("配置项-查看详情(配置项明细部分)成功");
        logger.info("结束 配置项-查看详情(配置项明细部分)");
        return ajaxResponse;
    }


    @GetMapping(value = "/loadConfigName")
    @ApiOperation(value = "配置项明细-配置项名称下拉列表", notes = "")
    @ApiImplicitParams({
            // 必须条件
            @ApiImplicitParam(name = "pageIndex", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示记录数", required = true, paramType = "query", dataType = "int"),
    })
    public AjaxResponse loadConfigName(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize, HttpServletRequest request) {
        logger.info("开始 配置项明细-配置项名称下拉列表");
        String userId = UserInfoUtil.getUserId(request, null);//当前登录用户ID
        //String userName = UserInfoUtil.getUserInfo(userId).getUserName();//当前登录用户姓名
        AjaxResponse ajaxResponse = new AjaxResponse();
        Page<ConfigNameDto> page = configService.loadConfigName(pageIndex, pageSize);
        ajaxResponse.setData(page);
        ajaxResponse.setSuccess(Boolean.TRUE);
        ajaxResponse.setMessage("查看配置项明细-配置项名称下拉列表成功");
        logger.info("结束 配置项明细-配置项名称下拉列表");
        return ajaxResponse;
    }

    @GetMapping(value = "/loadConfigDetailValue")
    @ApiOperation(value = "配置项明细-联动值下拉列表", notes = "")
    @ApiImplicitParams({
            // 必须条件
            @ApiImplicitParam(name = "pageIndex", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示记录数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "id", value = "配置项id", required = true, paramType = "query", dataType = "int"),
    })
    public AjaxResponse loadConfigDetailValue(@Valid ConfigPageSearchForm configPageSearchForm, HttpServletRequest request) {
        logger.info("开始 配置项明细-联动值下拉列表");
        String userId = UserInfoUtil.getUserId(request, null);//当前登录用户ID
        //String userName = UserInfoUtil.getUserInfo(userId).getUserName();//当前登录用户姓名
        AjaxResponse ajaxResponse = new AjaxResponse();
        Page<ConfigDetailValue> page = configService.loadConfigDetailValue(configPageSearchForm);
        ajaxResponse.setData(page);
        ajaxResponse.setSuccess(Boolean.TRUE);
        ajaxResponse.setMessage("V配置项明细-联动值下拉列表成功");
        logger.info("结束 配置项明细-联动值下拉列表");
        return ajaxResponse;
    }


    @PostMapping(value = "/add")
    @ApiOperation(value = "配置项-添加", notes = "")
    @ApiImplicitParams({
            // 必须条件
            @ApiImplicitParam(name = "code", value = "配置编码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "配置项名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "isLink", value = "是否联动", required = true, paramType = "query", dataType = "boolean"),
            @ApiImplicitParam(name = "system", value = "使用系统", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "commont", value = "备注", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "preColoum", value = "预留列", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "configDetailFormList", value = "配置项明细列表(;分隔)", required = true, paramType = "query")
    })
    public AjaxResponse add(@Valid ConfigForm form, HttpServletRequest request) {
        logger.info("开始 配置项添加");
        AjaxResponse ajaxResponse = new AjaxResponse();
        String userId = UserInfoUtil.getUserId(request, form.getCreateUserId());//当前登录用户ID
        //当前登录用户信息
        //SysUserInfo sysUserInfo = UserInfoUtil.getUserInfo(userId);

        SysUserInfo sysUserInfo = new SysUserInfo();
        sysUserInfo.setUserId("111");
        sysUserInfo.setUserName("测试");

        Assert.notNull(sysUserInfo, "用户信息为空");

        configService.add(sysUserInfo, form);
        ajaxResponse.setSuccess(Boolean.TRUE);
        ajaxResponse.setMessage("新增配置项添加成功");

        logger.info("结束 配置项添加");
        return ajaxResponse;
    }


    @PostMapping(value = "/edit")
    @ApiOperation(value = "配置项-修改", notes = "")
    @ApiImplicitParams({
            // 必须条件
            @ApiImplicitParam(name = "id", value = "配置项id", required = true, dataType = "String" ,paramType = "query"),
            @ApiImplicitParam(name = "code", value = "配置编码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "配置项名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "isLink", value = "是否联动", required = false, paramType = "query", dataType = "boolean"),
            @ApiImplicitParam(name = "system", value = "使用系统", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "commont", value = "备注", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "preColoum", value = "预留列", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "configDetailFormList", value = "配置项明细列表(;分隔)", required = false, paramType = "query")
    })
    public AjaxResponse edit(ConfigForm form, HttpServletRequest request) {
        logger.info("开始 配置项-修改");

        String userId = UserInfoUtil.getUserId(request, form.getCreateUserId());//当前登录用户ID

        SysUserInfo sysUserInfo = new SysUserInfo();
        sysUserInfo.setUserId("111");
        sysUserInfo.setUserName("测试");
        //SysUserInfo sysUserInfo = UserInfoUtil.getUserInfo(userId);//当前登录用户信息
        Assert.notNull(sysUserInfo, "用户信息为空");
        AjaxResponse ajaxResponse = new AjaxResponse();

        configService.updateConfig(form, sysUserInfo);
        ajaxResponse.setSuccess(Boolean.TRUE);
        ajaxResponse.setMessage("配置项-修改成功");

        logger.info("结束 配置项-修改");
        return ajaxResponse;
    }


    @GetMapping(value = "/logicalDeletion")
    @ApiOperation(value = "配置项-删除", notes = "")
    @ApiImplicitParams({
            // 必须条件
            @ApiImplicitParam(name = "ids", value = "删除配置项(逗号分隔)", required = true, paramType = "query", dataType = "String"),
    })
    public AjaxResponse logicalDeletion(@RequestParam("ids") String ids, HttpServletRequest request) {
        logger.info("开始 配置项-删除");
        //当前登录用户信息
        //SysUserInfo sysUserInfo = UserInfoUtil.getUserInfo(userId);

        SysUserInfo sysUserInfo = new SysUserInfo();
        sysUserInfo.setUserId("111");
        sysUserInfo.setUserName("测试");
      /*  String userId = UserInfoUtil.getUserId(request,null);
        //当前登录用户信息
        SysUserInfo sysUserInfo = UserInfoUtil.getUserInfo(userId);
        Assert.notNull(sysUserInfo ,"用户信息为空");*/
        AjaxResponse ajaxResponse = new AjaxResponse();
        if (StringUtils.isEmpty(ids)) {
            ajaxResponse.setMessage("请求参数为空");
            logger.error("非法的请求参数  {}", ajaxResponse.getMessage());
            return ajaxResponse;
        }
        configService.deleteConfigByIds(sysUserInfo, ids);
        ajaxResponse.setSuccess(Boolean.TRUE);
        ajaxResponse.setMessage("删除配置项成功");
        logger.info("结束 删除配置项");
        return ajaxResponse;
    }



    @GetMapping(value = "/test")
    public void test(String id){
        configService.test(id);
    }
}
