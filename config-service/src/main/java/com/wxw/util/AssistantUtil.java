package com.wxw.util;

import net.sf.json.JSONObject;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

/**
 * @Author:Created by wanxiaowei
 * @Description:  辅助处理工具
 * @Date:Created in 16:24 2017/9/12
 * @Modified By :
 */
public class AssistantUtil {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 把JSON数据转换成JAVA对象
     * description: 函数的目的/功能
     */
    public static void setJsonObjData(Object obj, JSONObject data, String[] excludes) throws Exception
    {


        // 反射获取所有方法
        Method[] methods = obj.getClass().getDeclaredMethods();
        if (null != methods)
        {

            for (Method m : methods)
            {

                String methodName = m.getName();

                if (methodName.startsWith("set"))
                {

                    methodName = methodName.substring(3);
                    // 获取属性名称
                    methodName = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);

                    if (!methodName.equalsIgnoreCase("class") && !isExistProp(excludes, methodName))
                    {

                        try
                        {

                            m.invoke(obj, new Object[] { data.get(methodName) });
                        }
                        catch (IllegalArgumentException e1)
                        {
                            if(m.getParameterTypes()[0].getName().equals("java.lang.Long")){
                                m.invoke(obj, new Object[] { Long.valueOf(data.get(methodName).toString())});
                            }else if(m.getParameterTypes()[0].getName().equals("java.util.Date")){
                                m.invoke(obj, new Object[] {data.containsKey(methodName)?  sdf.parse(data.get(methodName).toString()) : null});                            }
                        }
                        catch (Exception e)
                        {

                        }

                    }
                }
            }
        }

    }

    private static boolean isExistProp(String[] excludes, String prop)
    {

        if (null != excludes)
        {

            for (String exclude : excludes)
            {
                if (prop.equals(exclude))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
