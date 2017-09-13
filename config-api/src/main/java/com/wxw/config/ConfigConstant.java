package com.wxw.config;

public abstract class ConfigConstant {

    public static final String SCAN_BASE_PACKAGES = "com.wxw";

    public static final String MAPPER_BASE_PACKAGES = "com.wxw.mapper";

    public static final String CONTROLLER_BASE_PACKAGES = "com.wxw.admin.controller";

    public static final String DTO_BASE_PACKAGES = "com.wxw.dto.";

    public static final String FORM_BASE_PACKAGES = "com.wxw.web.form.";

    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";

    public static final String DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String TIME_FORMAT_PATTERN = "HH:mm:ss";

    public static final String JSONP_FUNCTION_NAME = "callback";

    public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "*";
    //.net解密的key(测试)
    public static final String TESTAESTKEY="EIKSFHAICq.s=w2zViefWiozhfe4,tw9";
    //.net解密的key(生产)
    public static final String BETAAESkEY = "SKBOAUSERAESKEY128821";

    public static final String FILEDOMAIN = "https://file.sellercube.com/";

    public static final  String ROUTEKEY = "app-config-manage";

    public static final  String SYSTEMNAME = "配置项管理";

    public static final String SPLIITSTR = ";;";

    public static final String DELETESPLIT = "::";

    public static final String UPDATESTAMENT="将【,】由【:】改为【::】;";
    //获取用户权限的url(测试)
    public static final  String TESTREQUESTURL = "http://192.168.1.147:8080/UserService/UserService.svc/GetPermissionByUserIdEx";
    //获取用户权限的url(生产)
    public static final  String BETAREQUESTURL = "https://erpapi.banggood.cn/UserService/UserService.svc/GetPermissionByUserIdEx";

    public static final String TOKENCOOKIENAME= "TOKEN_COOKIE_NAME";

    //测试环境
    public static final String TESTUSERINFOURL = "http://14.23.92.186:58080/HMSService/HMSService.svc/GetUserByUserInfo?pageIndex=1&pageSize=5&oaId=&userId=";
    //生产环境
    public static final String BETAUSERINFOURL="https://erpapi.banggood.cn/HMSService/HMSService.svc/GetUserByUserInfo?userId=";
}
