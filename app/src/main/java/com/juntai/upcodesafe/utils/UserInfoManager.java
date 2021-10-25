package com.juntai.upcodesafe.utils;

import com.juntai.upcodesafe.bean.TextKeyValueBean;
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
     * 是否是企业账户
     * @return
     */
    public static boolean   isEnterpriseAccount(){
        return  getAccountTypeId()==4;
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
    public static String getUnitName() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getUnitName() : "";
    }

    /**
     * 获取用户详情
     * @return
     */
    public static String getUserDetailInfo() {
        if (4== getAccountTypeId()) {
          return getUnitName() +"  "+ getUserName();
        }else if(1== getAccountTypeId()||2==getAccountTypeId()){
            return getDepartmentName()+"  "+getUserName();
        }else {
            return getTerritoryName()+"  "+getUserName();

        }
    }
    /**
     * 获取用户详情
     * @return
     */
    public static String getUserDetailInfoWithoutName() {
        if (4== getAccountTypeId()) {
          return getUnitName();
        }else if(1== getAccountTypeId()||2==getAccountTypeId()){
            return getDepartmentName();
        }else {
            return getTerritoryName();

        }
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
        return getUser() != null && getUser().getData() != null ? getDepartmentName()+"  "+getUserName() : "";
    }
    /**
     * getDepartmentId
     *
     * @return
     */
    public static String getDepartmentName() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getDepartmentName() : "";
    }
    public static String getTerritoryName() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getTerritoryName() : "";
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
