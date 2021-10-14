package com.juntai.upcodesafe.home_page;

import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.upcodesafe.AppNetModule;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.HomePageMenuBean;
import com.juntai.upcodesafe.bean.NoticeBean;
import com.juntai.upcodesafe.bean.UnitDetailBean;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;

/**
 * @aouther Ma
 * @date 2019/3/14
 */
public class HomePagePresent extends BasePresenter<IModel, HomePageContract.IHomePageView> implements HomePageContract.IHomePagePresent {
    @Override
    protected IModel createModel() {
        return null;
    }



    public List<HomePageMenuBean> getMenuList(){

        List<HomePageMenuBean> arrays = new ArrayList<>();

        arrays.add(new HomePageMenuBean(HomePageContract.HOMEPAGE_MENU_NOTICE, "Message\nNotification",R.drawable.sp_filled_gray_circle));
        arrays.add(new HomePageMenuBean(HomePageContract.HOMEPAGE_MENU_ENTERPRISE_CHECK, "Enterprise\nComprehensive", R.drawable.sp_filled_gray_circle));
        arrays.add(new HomePageMenuBean(HomePageContract.HOMEPAGE_MENU_WRING, "The accident\nWarning", R.drawable.sp_filled_gray_circle));
        arrays.add(new HomePageMenuBean(HomePageContract.HOMEPAGE_MENU_EDUCATION, "Online\nEducation", R.drawable.sp_filled_gray_circle));

        return arrays;
    }

    /**
     * 通知公告
     * @param requestBody
     * @param tag
     */
    public void getHomePageNotice(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getHomePageNotice(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<NoticeBean>(getView()) {
                    @Override
                    public void onSuccess(NoticeBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }
    public void getHomePageAccident(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getHomePageAccident(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<NoticeBean>(getView()) {
                    @Override
                    public void onSuccess(NoticeBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

}
