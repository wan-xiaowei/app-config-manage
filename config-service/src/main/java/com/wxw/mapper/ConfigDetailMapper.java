package com.wxw.mapper;


import com.wxw.model.ConfigDetail;
import com.wxw.model.ConfigDetailExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
@Mapper
public interface ConfigDetailMapper {
    long countByExample(ConfigDetailExample example);

    int deleteByExample(ConfigDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ConfigDetail record);

    int insertSelective(ConfigDetail record);

    List<ConfigDetail> selectByExampleWithRowbounds(ConfigDetailExample example, RowBounds rowBounds);

    List<ConfigDetail> selectByExample(ConfigDetailExample example);

    ConfigDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ConfigDetail record, @Param("example") ConfigDetailExample example);

    int updateByExample(@Param("record") ConfigDetail record, @Param("example") ConfigDetailExample example);

    int updateByPrimaryKeySelective(ConfigDetail record);

    int updateByPrimaryKey(ConfigDetail record);
}