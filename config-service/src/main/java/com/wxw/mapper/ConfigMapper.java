package com.wxw.mapper;


import com.wxw.dto.ConfigNameDto;
import com.wxw.model.Config;
import com.wxw.model.ConfigExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ConfigMapper {
    long countByExample(ConfigExample example);

    int deleteByExample(ConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Config record);

    int insertSelective(Config record);

    List<Config> selectByExampleWithRowbounds(ConfigExample example, RowBounds rowBounds);

    List<Config> selectByExample(ConfigExample example);

    Config selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Config record, @Param("example") ConfigExample example);

    int updateByExample(@Param("record") Config record, @Param("example") ConfigExample example);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);

    List<ConfigNameDto> selectConfigNameWithRowBounds(RowBounds rowBounds);
}