package com.wxw.web.form;


import com.wxw.exception.ParameterIncorrectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;

/**
 * Created by heweiming on 2017/5/18.
 */
public interface PageSearchForm {

    public static final Logger logger = LoggerFactory.getLogger(PageSearchForm.class);

    /**
     * 必须在实现类声明 private Integer pageIndex;
     */
    public static final String PAGE_INDEX_LABEL = "pageIndex";

    /**
     * 必须在实现类声明 private Integer pageSize
     */
    public static final String PAGE_SIZE_LABEL = "pageSize";

    @NotNull(message = "页数不能为空")
    @Min(value = 1, message = "页数必须大于或等于1")
    default Integer getPageIndex() {
        return handlerDefaultField(PAGE_INDEX_LABEL);
    }

    @NotNull(message = "每页显示记录数不能为空")
    @Min(value = 1, message = "每页显示记录数必须大于或等于1")
    default Integer getPageSize() {
        return handlerDefaultField(PAGE_SIZE_LABEL);
    }

    default void setPageIndex(Integer pageIndex) {
        assignmentValueForDefaultField(PAGE_INDEX_LABEL, pageIndex);
    }

    default void setPageSize(Integer pageSize) {
        assignmentValueForDefaultField(PAGE_SIZE_LABEL, pageSize);
    }

    default Field findDefaultField(String fieldName) throws IllegalArgumentException {
        Field field = ReflectionUtils.findField(this.getClass(), fieldName);
        if (field == null) {
            logger.error("请检查{} 类是否有 {} 属性", this.getClass().getSimpleName(), fieldName);
            return null;
        }
        return field;
    }

    default Integer handlerDefaultField(String fieldName) {
        Object param = null;
        try {
            Field field = findDefaultField(fieldName);
            field.setAccessible(true);
            param = field.get(this);
            if (param == null) {
                return null;
            } else {
                Integer pageSize = Integer.valueOf(param.toString());
                return pageSize;
            }
        } catch (IllegalAccessException e) {
            logger.error("{}参数 {} 类型不匹配或系统错误", fieldName, param,e);
            throw new ParameterIncorrectException(e.getMessage());
        }
    }

    default void assignmentValueForDefaultField(String fieldName, Integer value) {
        Field field = findDefaultField(fieldName);
        field.setAccessible(Boolean.TRUE);
        try {
            if (value == null) {
                field.set(this, null);
            } else {
                field.set(this, value);
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            logger.error("系统错误", e);
            throw new ParameterIncorrectException(e.getMessage());
        }
    }
    
}
