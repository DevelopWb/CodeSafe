package com.juntai.upcodesafe.entrance;


import android.annotation.SuppressLint;

import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.utils.PubUtil;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.upcodesafe.AppNetModule;
import com.juntai.upcodesafe.bean.UserBean;

import io.reactivex.functions.Consumer;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2020/3/5 15:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/5 15:55
 */
public class EntrancePresent extends BasePresenter<IModel, EntranceContract.IEntranceView> implements EntranceContract.IEntrancePresent {

    @Override
    protected IModel createModel() {
        return null;
    }


    @SuppressLint("CheckResult")
    @Override
    public void login(String account, String password,String tag) {
        AppNetModule
                .createrRetrofit()
                .login(account, password)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new Consumer<UserBean>() {
                    @Override
                    public void accept(UserBean userBean) throws Exception {
                        if (getView() != null) {
                            getView().hideLoading();
                            getView().onSuccess(tag, userBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (getView() != null) {
                            getView().hideLoading();
                            getView().onError(tag, PubUtil.ERROR_NOTICE);
                        }
                    }
                });
    }





}
