package com.wxw.service.impl;

import com.wxw.mapper.LegalOperateLogMapper;
import com.wxw.bean.Page;
import com.wxw.dto.LegalOperateLogDto;
import com.wxw.model.LegalOperateLog;
import com.wxw.model.LegalOperateLogExample;
import com.wxw.service.LegalOperateLogService;
import com.wxw.web.form.LegalOperateLog.LegalOperateLogPageSearchForm;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:Created by wanxiaowei
 * @Description:
 * @Date:Created in 18:42 2017/7/19
 * @Modified By :
 */
@Service
public class LegalOperateLogServiceImpl implements LegalOperateLogService {
    private static final Logger logger = LoggerFactory.getLogger(LegalOperateLogServiceImpl.class);

    @Autowired
    private LegalOperateLogMapper legalOperateLogMapper;

    @Override
    public Page<LegalOperateLogDto> loadLegalOperateLogByPage(LegalOperateLogPageSearchForm form) {
        logger.info("查询loadLegalOperateLogByPage开始 ");
        LegalOperateLogExample example = new LegalOperateLogExample();
        LegalOperateLogExample.Criteria criteria = example.createCriteria();
        criteria.andDataIdEqualTo(form.getDataId());
        criteria.andLogTypeEqualTo(form.getLogType());

        int startIndex = ( form.getPageIndex() - 1) *  form.getPageSize();
        RowBounds rowBounds = new RowBounds(startIndex, form.getPageSize());

        List<LegalOperateLog> logList = legalOperateLogMapper.selectByExampleWithRowbounds(example,rowBounds);
        long total = legalOperateLogMapper.countByExample(example);
        List<LegalOperateLogDto> operateLogDtoList = logList.stream().map(legalOperateLog ->{
            LegalOperateLogDto legalOperateLogDto = new LegalOperateLogDto();
            BeanUtils.copyProperties(legalOperateLog , legalOperateLogDto);
            return legalOperateLogDto;
        }).collect(Collectors.toList());
        Page<LegalOperateLogDto> page = new Page<>(form.getPageIndex(), form.getPageSize(), total, operateLogDtoList);
        logger.info("查询loadLegalOperateLogByPage结束", form);
        return page;
    }
}
