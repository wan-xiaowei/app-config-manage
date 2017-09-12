package com.wxw.admin.adivce;


import com.wxw.enums.Warn;
import com.wxw.exception.BaseException;
import com.wxw.web.json.AjaxResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * Controller异常处理功能类
 */
@RestControllerAdvice
public class ControllerExceptionHandler {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 将异常信息封装到AjaxResponse,返回给调用者
     *
     * @param e 异常对象
     * @return AjaxResponse
     */
    @ExceptionHandler
    public AjaxResponse exceptionHandler(Exception e) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setSuccess(Boolean.FALSE);
        logger.error(e.getMessage(), e);
        if (e instanceof BindException) {
            List<FieldError> errors = ((BindException) e).getFieldErrors();
            StringBuilder sb = new StringBuilder();
            for (FieldError fieldError : errors) {
                String defaultMessage = fieldError.getDefaultMessage();
                if(StringUtils.isEmpty(sb)){
                    sb.append(defaultMessage);
                }
            }
            ajaxResponse.setMessage("参数格式错误，请检查传递的参数 :" + sb.toString());
        } if(e instanceof BaseException){
            ajaxResponse.setMessage(Warn.SERVERWARN.getLabel());
        }else {
            // 错误原因
            ajaxResponse.setMessage(e.getMessage());
        }

        return ajaxResponse;
    }

}
