package com.wxw.mapper;

import com.wxw.model.LegalOperateLog;
import com.wxw.model.LegalOperateLogExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface LegalOperateLogMapper {
    long countByExample(LegalOperateLogExample example);

    int deleteByExample(LegalOperateLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(LegalOperateLog record);

    int insertSelective(LegalOperateLog record);

    List<LegalOperateLog> selectByExampleWithRowbounds(LegalOperateLogExample example, RowBounds rowBounds);

    List<LegalOperateLog> selectByExample(LegalOperateLogExample example);

    LegalOperateLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") LegalOperateLog record, @Param("example") LegalOperateLogExample example);

    int updateByExample(@Param("record") LegalOperateLog record, @Param("example") LegalOperateLogExample example);

    int updateByPrimaryKeySelective(LegalOperateLog record);

    int updateByPrimaryKey(LegalOperateLog record);
}