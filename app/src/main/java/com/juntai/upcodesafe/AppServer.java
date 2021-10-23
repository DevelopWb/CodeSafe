package com.juntai.upcodesafe;


import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.upcodesafe.bean.CheckDetailBean;
import com.juntai.upcodesafe.bean.CheckRecordBean;
import com.juntai.upcodesafe.bean.HomeBusinessBean;
import com.juntai.upcodesafe.bean.IdNameBean;
import com.juntai.upcodesafe.bean.LableBean;
import com.juntai.upcodesafe.bean.MyMsgBean;
import com.juntai.upcodesafe.bean.NoticeBean;
import com.juntai.upcodesafe.bean.RectifyNoticeDeatilBean;
import com.juntai.upcodesafe.bean.RectifyNoticeListBean;
import com.juntai.upcodesafe.bean.ResponseDetailBean;
import com.juntai.upcodesafe.bean.SearchBean;
import com.juntai.upcodesafe.bean.SearchResultBean;
import com.juntai.upcodesafe.bean.TownListBean;
import com.juntai.upcodesafe.bean.TrainPlanListBean;
import com.juntai.upcodesafe.bean.UnitDetailBean;
import com.juntai.upcodesafe.bean.UnitOfInductryBean;
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

    @POST(AppHttpPath.SEARCH)
    Observable<SearchBean> search(@Body RequestBody requestBody);

    @POST(AppHttpPath.SEARCH_MORE)
    Observable<SearchResultBean> searchMore(@Body RequestBody requestBody);

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

    /**
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.ADD_UNIT)
    Observable<BaseResult> ddUnit(@Body RequestBody requestBody);





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
    @POST(AppHttpPath.GET_BUSINESS_DIRECTOR)
    Observable<HomeBusinessBean> getHomePageBusinessDirector(@Body RequestBody requestBody);

    /**
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.GET_BUSINESS_SUPERVISE)
    Observable<HomeBusinessBean> getHomePageBusinessSupervise(@Body RequestBody requestBody);

    /**
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.GET_BUSINESS_TERRITORY)
    Observable<HomeBusinessBean> getHomePageBusinessTerritory(@Body RequestBody requestBody);

    /**
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.GET_BUSINESS_GRID)
    Observable<HomeBusinessBean> getHomePageBusinessGrid(@Body RequestBody requestBody);

    /**
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.GET_BUSINESS_TYPES)
    Observable<IdNameBean> getBusinessTypes(@Body RequestBody requestBody);

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
    @POST(AppHttpPath.GET_ENTERPRIZSE_INFO_BY_UUID)
    Observable<UnitDetailBean> getEnterpriseInfoByUUID(@Body RequestBody requestBody);
    /**
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.EDIT_UNIT_INFO)
    Observable<BaseResult> editUnitInfo(@Body RequestBody requestBody);
    /**
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.DELETE_UNIT_MANAGER)
    Observable<BaseResult> deleteUnitManager(@Body RequestBody requestBody);

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
    @POST(AppHttpPath.GET_RESPONSE_DETAIL)
    Observable<ResponseDetailBean> getResponseDetail(@Body RequestBody requestBody);

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

    /**
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.CHECK_RECORD_DETAIL)
    Observable<CheckDetailBean> getCheckRecordDetail(@Body RequestBody requestBody);

    /**
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.ADD_PUNISH)
    Observable<BaseResult> addPunishInfo(@Body RequestBody requestBody);

    /**
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.ADD_RECTIFY_NOTICE)
    Observable<BaseResult> addRectifyNotice(@Body RequestBody requestBody);

    /**
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.RECTIFY_NOTICE_DETAIL)
    Observable<RectifyNoticeDeatilBean> getRectifyNoticeDetail(@Body RequestBody requestBody);

    /**
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.GET_TRAIN_PLAN_LIST)
    Observable<TrainPlanListBean> getTrainPlanList(@Body RequestBody requestBody);

    @POST(AppHttpPath.ADD_TRAIN_PLAN)
    Observable<BaseResult> addTrainPlans(@Body RequestBody requestBody);

    /**
     * 获取tab标签
     *
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.GET_EDUCATION_TAG)
    Observable<LableBean> getEducationTag(@Body RequestBody requestBody);


    @POST(AppHttpPath.GET_EDUCATION_LIST)
    Observable<NoticeBean> getEducationList(@Body RequestBody requestBody);

    @POST(AppHttpPath.GET_NORMAL_CHECK_LIST)
    Observable<UnitOfInductryBean> getNormalCheckList(@Body RequestBody requestBody);

    @POST(AppHttpPath.GET_SPECIAL_CHECK_LIST)
    Observable<UnitOfInductryBean> getSpecialCheckList(@Body RequestBody requestBody);

    @POST(AppHttpPath.MY_NEWS)
    Observable<MyMsgBean> getMyMSG(@Body RequestBody requestBody);
    @POST(AppHttpPath.MY_NEWS_UNREAD)
    Observable<BaseResult> getMyMsgUnread(@Body RequestBody requestBody);
}