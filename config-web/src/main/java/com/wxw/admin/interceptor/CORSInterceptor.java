package com.wxw.admin.interceptor;

import com.wxw.config.ConfigConstant;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author heweiming 2017年6月7日下午3:39:55
 * @description 跨域请求处理
 * @version 1.0
 */
public class CORSInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // TODO 1.参数可配置化 2.生产环境明确值
        response.setHeader("Access-Control-Allow-Origin", ConfigConstant.ACCESS_CONTROL_ALLOW_ORIGIN);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}
