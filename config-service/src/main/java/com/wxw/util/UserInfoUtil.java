package com.wxw.util;

import com.wxw.bean.SysUserInfo;
import com.wxw.config.ConfigConstant;
import com.wxw.dto.SysPermissionTextValue;
import com.wxw.exception.UserNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/7/2.
 */
public class UserInfoUtil {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoUtil.class);

    private static final String requestUrl = ConfigConstant.BETAAESkEY;
    private static final String systemCode = ConfigConstant.ROUTEKEY;

    private UserInfoUtil() {

    }

    public static String getUserId(HttpServletRequest request, String userId) {
        try {
            if(StringUtils.isEmpty(userId)) {
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    logger.info("cookie.length {}", cookies.length);
                    for (Cookie cookie : cookies) {
                        //测试环境
                        if (cookie.getName().equalsIgnoreCase(ConfigConstant.TOKENCOOKIENAME)) {
                            //AESKey的解密文
                            userId = null;
                            logger.info("currentUserId {}", userId);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("获取用户id失败 {}",userId , e);
            throw new UserNotFoundException("获取用户id失败");
        }
        return userId;
    }

    public static SysUserInfo getUserInfo(String userId) {
        SysUserInfo currentUserInfo;
        try {
            //TODO
            currentUserInfo = null;
        } catch (Exception e) {
            logger.error("获取用户信息失败 {}",userId, e);
            throw new UserNotFoundException("获取用户信息失败");
        }
        return currentUserInfo;
    }


    public static Map<String,Boolean> getUserPermissons(String userId , HttpServletRequest request , String permissionId) {
        Assert.notNull(permissionId, "permissionId不能为空");
        List<String> permissionItemIds = (List<String>) request.getServletContext().getAttribute("permissionItemIds");
        try {
            String[] permissionIdArr = permissionId.split(",");
            //TODO
            List<SysPermissionTextValue> currentUserPermissionList = null;
            List<String> list = currentUserPermissionList.stream().map(sysPermissionTextValue -> sysPermissionTextValue.getValue()).collect(Collectors.toList());
            permissionItemIds.removeAll(list);
            /*
             **该系统的权限（部分需要控制的url）与该用户权限(能访问的url)的差集
             **若URI在这个差集中,则拦截
             */
            Map<String, Boolean> map = Arrays.stream(permissionIdArr).collect(Collectors.toMap(perId -> perId, perId -> !permissionItemIds.contains(UriReplaceUtil.replaceUri(request.getContextPath() + "/" + perId))));
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取用户权限失败 {} {}", userId,permissionId,e);
            throw new UserNotFoundException("获取用户权限失败");
        }
    }
}