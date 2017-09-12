package com.wxw.mybatis.typehandler;


import com.wxw.enums.DisplayedEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 自定义枚举处理类
 *
 * @author heweiming
 */
public class EnumCodeTypeHandler<E extends Enum<E> & DisplayedEnum<E>> extends BaseTypeHandler<E> {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(EnumCodeTypeHandler.class);

    private Class<E> type;

    public EnumCodeTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("参数类型不能为空");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            try {
                return DisplayedEnum.valueOfEnum(type, value ,null);
            } catch (Exception ex) {
                throw new IllegalArgumentException("未知的枚举类型： " + value + " ,请核对 " + type.getSimpleName());
            }
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            try {
                return DisplayedEnum.valueOfEnum(type, value , null);
            } catch (Exception ex) {
                throw new IllegalArgumentException("未知的枚举类型： " + value + " ,请核对 " + type.getSimpleName());
            }
        }
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            try {
                return DisplayedEnum.valueOfEnum(type, value ,null);
            } catch (Exception ex) {
                throw new IllegalArgumentException("未知的枚举类型： " + value + " ,请核对 " + type.getSimpleName());
            }
        }
    }


}