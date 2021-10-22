package com.juntai.upcodesafe.utils;

import com.juntai.upcodesafe.bean.UserBean;
import com.orhanobut.hawk.Hawk;

/**
 * @Author: tobato
 * @Description: 作用描述  用户信息管理类
 * @CreateDate: 2020/12/19 14:04
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/12/19 14:04
 */
public class UserInfoManager {
    public static String QQ_ID = null;//qqid
    public static String WECHAT_ID = null;//wechatid
    public static String OTHER_NICK_NAME = null;//第三方昵称

    /**
     * 获取用户信息
     *
     * @return
     */
    public static UserBean getUser() {
        UserBean userBean = Hawk.get(HawkProperty.LOGIN_KEY);
        return userBean;
    }

    /**
     * 判定用户是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        if (getUser() == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取账户
     *
     * @return
     */
    public static String getUserAccount() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getAccount() : "";
    }
    /**
     * 获取账户
     *
     * @return
     */
    public static String getUserName() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getNickname() : "";
    }
    /**
     * 获取usertoken
     *
     * @return
     */
    public static String getUserToken() {
        return Hawk.get(HawkProperty.TOKEN_KEY);
    }

    /**
     * 获取getUserId
     *
     * @return
     */
    public static int getUserId() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getUserId() : -1;
    }
    /**
     * getDepartmentId
     *
     * @return
     */
    public static int getDepartmentId() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getDepartmentId() : -1;
    }
    /**
     * getDepartmentId
     *
     * @return
     */
    public static String getDepartmentDetailInfo() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getDepartmentName()+getUser().getData().getDepartmentSecondId() : "";
    }
    /**
     * getDepartmentId
     *
     * @return
     */
    public static String getDepartmentName() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getDepartmentName() : "";
    }
    /**
     * getTerritoryId
     *
     * @return
     */
    public static int getTerritoryId() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getTerritoryId() : -1;
    }
    /**
     * getGridId
     *
     * @return
     */
    public static int getGridId() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getGridId() : -1;
    }
    /**
     *
     * @return
     */
    public static int getUnitId() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getUnitId() : -1;
    }
    /**
     * 获取getUserId
     *  1监管部门权限；2属地权限；3网格员；4企业人员）
     * @return
     */
    public static int getAccountTypeId() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getTypeId() : 0;
    }

    /**
     * 获取检查类型
     *
     * @return
     */
    public static int getCheckType() {
        int type = 0;
        switch (getAccountTypeId()) {
            case 1:
                type = 2;
                break;
            case 2:
                type = 3;
                break;
            case 3:
                type = 4;
                break;
            case 4:
                type = 1;
                break;
            default:
                break;
        }
        return type;
    }
    /**
     * getAccountStatus
     *账号审核状态（1审核中；2审核成功；3未提交或审核失败）
     * @return
     */
    public static int getAccountStatus() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getState() : 3;
    }

}
