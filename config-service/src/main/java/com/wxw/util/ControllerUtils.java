package com.wxw.util;


import com.wxw.web.json.AjaxResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * Created by heweiming on 2017/5/18.
 */
public abstract class ControllerUtils {

    private ControllerUtils(){

    }
    /**
     * 将验证错误信息封装到AjaxResponse
     *
     * @param result       model验证结果对象
     * @param ajaxResponse 数据封装对象
     * @return AjaxResponse
     */
    public static AjaxResponse validModelForAjaxResponse(BindingResult result, AjaxResponse ajaxResponse) {
        StringBuilder errorMsg = new StringBuilder();
        List<FieldError> fieldErrors = result.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errorMsg.append(fieldError.getDefaultMessage());
            errorMsg.append("  ");
        }
        ajaxResponse.setSuccess(Boolean.FALSE);
        ajaxResponse.setMessage(errorMsg.toString());
        return ajaxResponse;
    }


}
