package com.juntai.upcodesafe.utils;


import com.juntai.upcodesafe.bean.UnitDetailBean;
import com.orhanobut.hawk.Hawk;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/2/27 10:59
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/2/27 10:59
 */
public class HawkProperty {

    //0 代表企业  1代表非企业  监督部门
    public static String ACCOUNT_TYPE = "account_type";
    //常规检查或者专项检查的标识

    public static String IS_NORMAL_CHECK = "checktype";
    //1开始检查
    //2培训计划
    public static int UPLOAD_TYPE = 1;
    public static String IS_YANSHOU_CHECK = "checktypeYanshou";
    public final static String LOGIN_KEY = "login_bean";
    public final static String TOKEN_KEY = "token_key";
    //当前企业信息
    public final static String UNIT_KEY = "unit_key";
    //行业种类
    public final static String BUSINESS_TYPES_KEY = "business_types_key";
    public final static String START_CHECK = "startcheck";
    public final static String START_CHECK_ISOK = "startcheckisok";

    public static String ADD_UNIT_KEY = "add_unit";//手动添加单位  这个key唯一  搜索添加的时候 需要绑定单位的id
    public static String EDIT_UNIT_KEY = "edit_unit";//编辑单位  这个key唯一  搜索添加的时候 需要绑定单位的id


    /**
     * 获取企业详情
     *
     * @return
     */
    public static UnitDetailBean.DataBean getUnitBean() {
        return Hawk.get(UNIT_KEY);
    }
}
