package com.juntai.upcodesafe;


import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.upcodesafe.bean.CheckRecordBean;
import com.juntai.upcodesafe.bean.IdNameBean;
import com.juntai.upcodesafe.bean.NoticeBean;
import com.juntai.upcodesafe.bean.RectifyNoticeListBean;
import com.juntai.upcodesafe.bean.TownListBean;
import com.juntai.upcodesafe.bean.UnitDetailBean;
import com.juntai.upcodesafe.bean.UnitsBean;
import com.juntai.upcodesafe.bean.UserBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * responseBody里的数据只能调用(取出)一次，第二次为空。可赋值给新的变量使用
 */
public interface AppServer {
    /**
     * 登录
     *
     * @param account
     * @param password
     * @return
     */
    @POST(AppHttpPath.LOGIN)
    Observable<UserBean> login(@Query("account") String account, @Query("password") String password
    );

    /**
     * 注册
     *
     * @param account
     * @param password
     * @param code
     * @return
     */
    @POST(AppHttpPath.REGIST)
    Observable<BaseResult> regist(@Query("account") String account, @Query("password") String password, @Query("code") String code
    );

    @POST(AppHttpPath.MODIFY_PWD)
    Observable<BaseResult> modifyPwd(@Query("account") String account, @Query("password") String password, @Query("code") String code
    );


    @POST(AppHttpPath.LOGOUT)
    Observable<BaseResult> logout(@Body RequestBody requestBody);

    /**
     * 获取用户信息
     *
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.USER_INFO)
    Observable<UserBean> getUserInfo(@Body RequestBody requestBody);


    /**
     * account  手机号
     *
     * @return
     */
    @POST(AppHttpPath.GET_SMS_CODE)
    Observable<BaseResult> getSMSCode(@Query("account") String account);


    /**
     * 获取用户信息
     *
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.ADD_INFO)
    Observable<BaseResult> addInfo(@Body RequestBody requestBody);

    /**
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.SEARCH_ACCOUNT_NATURE)
    Observable<UnitsBean> searchAccountNature(@Body RequestBody requestBody);

    /**
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.GET_NEXT_DEPARTMENT)
    Observable<IdNameBean> getNextDepartment(@Body RequestBody requestBody);

    /**
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.GET_TOWN_LIST)
    Observable<TownListBean> getTownList(@Body RequestBody requestBody);

    /**
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.MANUAL_ADD_UNIT)
    Observable<BaseResult> manualAddUnit(@Body RequestBody requestBody);





    /*====================================================    首页   ==============================================================*/

    /**
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.HOMEPAGE_NOTICE)
    Observable<NoticeBean> getHomePageNotice(@Body RequestBody requestBody);

    /**
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.HOMEPAGE_ACCIDENT)
    Observable<NoticeBean> getHomePageAccident(@Body RequestBody requestBody);

    /**
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.GET_ENTERPRIZSE_INFO)
    Observable<UnitDetailBean> getEnterpriseInfo(@Body RequestBody requestBody);
    /**
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.GET_RESPONSE_LIST)
    Observable<IdNameBean> getResponseList(@Body RequestBody requestBody);
    /**
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.GET_RECTIFY_NOTICE_LIST)
    Observable<RectifyNoticeListBean> getRectifyNoticeList(@Body RequestBody requestBody);
    /**
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.START_INSPECT)
    Observable<BaseResult> startInspect(@Body RequestBody requestBody);
    /**
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.GET_CHECK_LIST)
    Observable<CheckRecordBean> getCheckList(@Body RequestBody requestBody);
}