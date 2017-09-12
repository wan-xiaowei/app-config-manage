package com.wxw.service;


import com.wxw.bean.Page;
import com.wxw.dto.LegalOperateLogDto;
import com.wxw.web.form.LegalOperateLog.LegalOperateLogPageSearchForm;

/**
 * @Author:Created by wanxiaowei
 * @Description:
 * @Date:Created in 18:42 2017/7/19
 * @Modified By :
 */
public interface LegalOperateLogService {
    Page<LegalOperateLogDto> loadLegalOperateLogByPage(LegalOperateLogPageSearchForm form);
}
