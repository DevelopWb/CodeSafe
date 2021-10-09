package com.juntai.upcodesafe;

public class AppHttpPath {

    public static final String BASE = "http://192.168.124.118:8080/EmergencyBureau";
    /**
     * 登录
     */
    public static final String LOGIN = BASE + "/u/appUserStaff/login.shtml";
    /**
     * 注册
     */
    public static final String REGIST = BASE + "/u/appUserStaff/register.shtml";


    /**
     * 检查更新
     */
//    public static final String APP_UPDATE = BASE + "/detectionAppVersions.shtml";
    public static final String APP_UPDATE = BASE + "";

    /**
     * 获取短信验证码
     */
    public static final String GET_SMS_CODE = BASE + "/u/appUserStaff/getSMSCode.shtml";
}