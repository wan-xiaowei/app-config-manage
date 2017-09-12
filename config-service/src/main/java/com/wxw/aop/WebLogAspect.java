package com.wxw.aop;


import com.wxw.bean.SysUserInfo;
import com.wxw.mapper.LegalOperateLogMapper;
import com.wxw.util.CommonUtils;
import com.wxw.util.DateUtils;
import com.wxw.util.ReflectionUtils;
import com.wxw.config.ConfigConstant;
import com.wxw.enums.DisplayedEnum;
import com.wxw.enums.LogType;
import com.wxw.enums.OperateType;
import com.wxw.enums.Warn;
import com.wxw.exception.OperateLogException;
import com.wxw.exception.ServiceException;
import com.wxw.model.LegalOperateLog;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 增,删,改 日志切面
 */
@Aspect
@Component
public class WebLogAspect {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Autowired
    LegalOperateLogMapper legalOperateLogMapper;

    @Pointcut("execution(* com.wxw.service.impl.*.add*(..)) ||  execution(* com.wxw.service.impl.*.update*(..))  ||  execution(* com.wxw.service.impl.*.delete*(..))")
    public void webLog() {
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(JoinPoint joinPoint, Object ret) throws Throwable {

        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

        //拦截的实体类
        Object target = joinPoint.getTarget();

        //拦截的方法名称
        String methodName = joinPoint.getSignature().getName();
        logger.info("methodName : " +methodName);
        //拦截的方法参数
        Object[] paramters = joinPoint.getArgs();

        //拦截的参数类型
        Class[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();

        Method method = target.getClass().getMethod(methodName, parameterTypes);
        //Api api = target.getClass().getAnnotation(Api.class);
        //ApiOperation operation = method.getAnnotation(ApiOperation.class);
        try {
            if (ret instanceof Optional && ((Optional) ret).isPresent()) {
                List<LegalOperateLog> legalOperateLogs = getOperateInfo(methodName, paramters, target, ret);
                //保存日志，可以换redis或mongdb等其它方式
                if (CommonUtils.isListNotNull(legalOperateLogs)) {
                    for (LegalOperateLog legalOperateLog : legalOperateLogs)
                        legalOperateLogMapper.insert(legalOperateLog);
                }
            } else {
                logger.error("doAfterReturning ret is null");
                throw new ServiceException(methodName + Warn.LOGWARN.getLabel());
            }
        } catch (OperateLogException e) {
            logger.error(Warn.LOGWARN.getLabel(), e);
            throw new ServiceException(methodName + Warn.LOGWARN.getLabel());
        }
    }


    /**
     * @param methodName 方法名
     * @param paramters  方法参数
     * @param target     目标对象
     * @return LegalOperateLog(日志对象)
     */
    private List<LegalOperateLog> getOperateInfo(String methodName, Object[] paramters, Object target, Object ret) {
        List<LegalOperateLog> legalOperateLogList = new ArrayList<>();
        if (((Optional) ret).isPresent()) {
            String[] retArr = ((Optional) ret).get().toString().split(ConfigConstant.DELETESPLIT);
            for (String result : retArr) {
                LegalOperateLog legalOperateLog = new LegalOperateLog();
                legalOperateLog.setId(CommonUtils.getUUID());
                legalOperateLog.setOperateTime(new Date());
                String dataId = getDataId(result);
                Assert.notNull(dataId, "getOperateInfo dataId is null");
                legalOperateLog.setDataId(dataId);
                String details= getDetais(methodName, result, target);
                Assert.notNull(dataId, "getOperateInfo details is null");
                legalOperateLog.setOperateDetails(details);
                LogType logType = getLogType(target);
                logger.info("logType {}", logType.getLabel());
                legalOperateLog.setLogType(logType);
                legalOperateLog.setOperateType(getOperateType(methodName).get());

                for (Object paramter : paramters) {
                    if (paramter instanceof SysUserInfo) {
                        legalOperateLog.setOperateId(((SysUserInfo) paramter).getUserId());
                        legalOperateLog.setOperateUserName(((SysUserInfo) paramter).getUserName());
                    }
                }
                legalOperateLogList.add(legalOperateLog);
            }
        }

        return legalOperateLogList;
    }

    /**
     * 根据方法名获取操作类型
     *
     * @param methodName 方法名
     * @return
     */
    private Optional<String> getOperateType(String methodName) {
       /* OperateType operateType= DisplayedEnum.valueOfEnum(OperateType.class, null, methodName);
        if(StringUtils.isNotEmpty(operateType.getLabel())){
            return Optional.of(operateType.getLabel());
        }*/
        if (methodName.startsWith(OperateType.ADD.getValue())) {
            return Optional.of(OperateType.ADD.getLabel());
        } else if (methodName.startsWith(OperateType.UPDATE.getValue())) {
            return Optional.of(OperateType.UPDATE.getLabel());
        } else
            return Optional.of(OperateType.DELETE.getLabel());


    }

    /**
     * 根据目标对象(serviceImpl)获得日志类型
     *
     * @param target 目标对象
     * @return
     */
    private LogType getLogType(Object target) {
        String targetValue = target.getClass().getSimpleName().replace("ServiceImpl", "");
        if (targetValue.endsWith("Account")) {
            return DisplayedEnum.valueOfEnum(LogType.class, null, "Account");
        }
        return DisplayedEnum.valueOfEnum(LogType.class, null, targetValue);
    }

    /**
     * 根据返回值获取dataId
     *
     * @param ret
     * @return
     */
    private String getDataId(Object ret) {
        if (ret instanceof String && ret != null) {
            String[] retArr = ((String) ret).split(ConfigConstant.SPLIITSTR);
            return retArr[0];
        }
        return null;
    }

    /**
     * @param methodName
     * @param result
     * @return
     */
    private String getDetais(String methodName, String result, Object target) {
        if (StringUtils.isBlank(result)) {
            throw new ServiceException(methodName + Warn.LOGWARN.getLabel());
        }
        if (methodName.startsWith("add")) {
            return "新增数据";
        } else if (methodName.startsWith("update")) {
            String[] resultArr = result.split(ConfigConstant.SPLIITSTR);
            //0:dataId  1:form   2:dto
            if (resultArr.length != 3) {
                throw new ServiceException(methodName + Warn.LOGWARN.getLabel());
            }
            String logStr = getLogStr(resultArr[1], resultArr[2], target);
            return logStr;
        } else if (methodName.startsWith("delete")) {
            return "删除数据";
        }
        return null;
    }


    private String getLogStr(String form, String dto, Object target) {
        StringBuilder sb = new StringBuilder();
        try {
            String targetValue = target.getClass().getSimpleName().replace("ServiceImpl", "");
            Class dtoClass = Class.forName(ConfigConstant.DTO_BASE_PACKAGES + targetValue + "Dto");
            Class formClass = Class.forName(ConfigConstant.FORM_BASE_PACKAGES + targetValue + "." + targetValue + "Form");
            JSONObject formObject = JSONObject.fromObject(form);
            JSONObject dtoObject = JSONObject.fromObject(dto);
            Object formObj = JSONObject.toBean(formObject, formClass);
            Object dtoObj = JSONObject.toBean(dtoObject, dtoClass);
            List<Field> fields = ReflectionUtils.getMappedFiled(formObj.getClass(), null);
            for (Field field : fields) {
                field.setAccessible(true);
                if (field != null) {
                    Object formValue = field.get(formObj);
                    if(formValue instanceof  Date){
                        formValue =  DateUtils.dateToStr((Date)formValue,"yyyy-MM-dd HH:mm:ss");
                    }
                    if (formValue != null) {
                        String fieldName = field.getName();
                        logger.info("fieldName {}" ,fieldName);
                        Object dtoValue = ReflectionUtils.getFieldValue(dtoObj, fieldName);
                        if(dtoValue instanceof  Date){
                            dtoValue = DateUtils.dateToStr((Date)dtoValue,"yyyy-MM-dd HH:mm:ss");
                        }
                        //Object dtoValue=ReflectionUtils.getFieldLabels(dtoObj , fieldName);
                        if (!formValue.equals(dtoValue)) {
                            String logValue = ReflectionUtils.getLogAnnotationValue(dtoObj, fieldName);
                            if(dtoValue==null)
                                dtoValue="";
                            if(logValue!=null)
                                sb.append(ConfigConstant.UPDATESTAMENT.replaceFirst(",", logValue).replaceFirst(":", dtoValue.toString()).replaceFirst("::", formValue.toString()));
                            logger.info("sb {}",  sb);
                        }
                    }
                }
            }
            if (StringUtils.isEmpty(sb)) {
                logger.error("getLogStr is null");
                throw new OperateLogException("getLogStr :" + Warn.LOGWARN.getLabel());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new OperateLogException("getLogStr :" + Warn.LOGWARN.getLabel());
        }
        return sb.toString();
    }

}