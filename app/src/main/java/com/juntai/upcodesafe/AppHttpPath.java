package com.juntai.upcodesafe;

public class AppHttpPath {

    public static final String BASE = "http://192.168.124.118:8080/EmergencyBureau";


    public static final String BASE_IMAGE = "http://192.168.124.118:9597";











    /**
     * 登录
     */
    public static final String LOGIN = BASE + "/u/appUserStaff/login.shtml";
    /**
     * 用户详情
     */
    public static final String USER_INFO = BASE + "/u/appUserStaff/selectUserInfo.shtml";
    /**
     * 注册
     */
    public static final String REGIST = BASE + "/u/appUserStaff/register.shtml";
    /**
     */
    public static final String MODIFY_PWD = BASE + "/u/appUserStaff/updatePassword.shtml";
    /**
     *
     */
    public static final String LOGOUT = BASE + "/u/appUserStaff/logout.shtml";


    /**
     * 检查更新
     */
//    public static final String APP_UPDATE = BASE + "/detectionAppVersions.shtml";
    public static final String APP_UPDATE = BASE + "";

    /**
     * 获取短信验证码
     */
    public static final String GET_SMS_CODE = BASE + "/u/appUserStaff/getSMSCode.shtml";




    /**
     * 提交补充信息
     */
    public static final String ADD_INFO = BASE + "/u/appUserStaff/addUserStaff.shtml";
    /**
     * 查找账号性质
     */
    public static final String SEARCH_ACCOUNT_NATURE = BASE + "/u/appUserStaff/selectAccountNature.shtml";
    /**
     * 监管单位二级部门
     */
    public static final String GET_NEXT_DEPARTMENT = BASE + "/u/appUserStaff/selectSuperviseUnitSecond.shtml";


    /**
     */
    public static final String GET_TOWN_LIST = BASE + "/u/appUserStaff/selectTerritoryList.shtml";


    /**
     * 新增企业
     */
    public static final String MANUAL_ADD_UNIT = BASE + "/u/appUserStaff/addSupplementUnit.shtml";


    /**
     * 通知公告
     */
    public static final String HOMEPAGE_NOTICE = BASE + "/u/appUnit/selectNotice.shtml";


    /**
     * 事故警示列表
     */
    public static final String HOMEPAGE_ACCIDENT = BASE + "/u/appUnit/selectAccidentWarningList.shtml";




}