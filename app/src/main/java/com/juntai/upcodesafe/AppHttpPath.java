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
     *
     */
    public static final String MODIFY_PWD = BASE + "/u/appUserStaff/updatePassword.shtml";
    /**
     *
     */
    public static final String LOGOUT = BASE + "/u/appUserStaff/logout.shtml";


    /**
     * 搜索
     */
    public static final String SEARCH = BASE + "/u/appUnit/search.shtml";
    /**
     * 搜索
     */
    public static final String SEARCH_MORE = BASE + "/u/appUnit/searchMore.shtml";


    /**
     * 检查更新
     */
//    public static final String APP_UPDATE = BASE + "/u/appUserStaff/detectionAppVersions.shtml";
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
     *
     */
    public static final String GET_TOWN_LIST = BASE + "/u/appUserStaff/selectTerritoryList.shtml";


    /**
     * 新增企业  补充资料时
     */
    public static final String MANUAL_ADD_UNIT = BASE + "/u/appUserStaff/addSupplementUnit.shtml";
    /**
     * 新增企业   监管单位添加的时候
     */
    public static final String ADD_UNIT = BASE + "/u/appUnit/addUnit.shtml";


    /**
     * 通知公告
     */
    public static final String HOMEPAGE_NOTICE = BASE + "/u/appUnit/selectNotice.shtml";


    /**
     * 事故警示列表
     */
    public static final String HOMEPAGE_ACCIDENT = BASE + "/u/appUnit/selectAccidentWarningList.shtml";

    /**
     * 企业详情
     */
    public static final String GET_ENTERPRIZSE_INFO = BASE + "/u/appUnit/selectUnitInfo.shtml";


    /**
     * 企业详情
     */
    public static final String GET_ENTERPRIZSE_INFO_BY_UUID = BASE + "/u/appUnit/selectUnitByUuid.shtml";
    /**
     * 修改企业详情
     */
    public static final String EDIT_UNIT_INFO = BASE + "/u/appUserStaff/updateUnit.shtml";


    /**
     * 责任清单列表
     */
    public static final String GET_RESPONSE_LIST = BASE + "/u/appUnit/selectUnitDetailedList.shtml";
    /**
     * 责任清单详情
     */
    public static final String GET_RESPONSE_DETAIL = BASE + "/u/appUnit/selectUnitDetailedInfo.shtml";


    /**
     * 限期整改指令书列表
     */
    public static final String GET_RECTIFY_NOTICE_LIST = BASE + "/u/appUnit/selectUnitNoticeList.shtml";




    /*====================================================    企业   ==============================================================*/

    /**
     * 开始检查
     */
    public static final String START_INSPECT = BASE + "/u/appUnit/inspectUnit.shtml";
    /**
     * 检查列表
     */
    public static final String GET_CHECK_LIST = BASE + "/u/appUnit/selectInspectRecordList.shtml";
    /**
     * 检查记录详情
     */
    public static final String CHECK_RECORD_DETAIL = BASE + "/u/appUnit/selectInspectRecordInfo.shtml";

    public static final String ADD_PUNISH = BASE + "/u/appUnit/addUnitPunish.shtml";

    public static final String ADD_RECTIFY_NOTICE = BASE + "/u/appUnit/addUnitNotice.shtml";
    public static final String RECTIFY_NOTICE_DETAIL = BASE + "/u/appUnit/selectUnitNoticeInfo.shtml";


    /**
     * 培训计划列表
     */
    public static final String GET_TRAIN_PLAN_LIST = BASE + "/u/appUnit/selectTrainPlanList.shtml";
    /**
     * 添加培训计划
     */
    public static final String ADD_TRAIN_PLAN = BASE + "/u/appUnit/addTrainPlan.shtml";


    /**
     * 在线教育标签
     */
    public static final String GET_EDUCATION_TAG = BASE + "/u/appUnit/selectEducationArticleLabel.shtml";
    /**
     * 在线教育列表
     */
    public static final String GET_EDUCATION_LIST = BASE + "/u/appUnit/selectEducationArticleList.shtml";





    /*====================================================    主管首页   ==============================================================*/


    public static final String GET_BUSINESS_DIRECTOR = BASE + "/u/appUnit/selectUnitTypeDirector.shtml";
    public static final String GET_BUSINESS_SUPERVISE = BASE + "/u/appUnit/selectUnitTypeSupervise.shtml";
    public static final String GET_BUSINESS_TERRITORY = BASE + "/u/appUnit/selectUnitTypeTerritory.shtml";
    public static final String GET_BUSINESS_GRID = BASE + "/u/appUnit/selectUnitTypeGrid.shtml";


    /**
     * 行业类型
     */
    public static final String GET_BUSINESS_TYPES = BASE + "/u/appUnit/selectUnitType.shtml";

    /**
     * 常规检查
     */
    public static final String GET_NORMAL_CHECK_LIST = BASE + "/u/appUnit/selectUnitRoutine.shtml";
    /**
     * 专项检查
     */
    public static final String GET_SPECIAL_CHECK_LIST = BASE + "/u/appUnit/selectUnitSpecial.shtml";


    /**
     * 我的消息
     */

    public static final String MY_NEWS = BASE + "/u/appUserStaff/selectUserMessage.shtml";

    /**
     * 我的消息 未读数
     */

    public static final String MY_NEWS_UNREAD = BASE + "/u/appUserStaff/selectUserUnreadMessageCount.shtml";


}