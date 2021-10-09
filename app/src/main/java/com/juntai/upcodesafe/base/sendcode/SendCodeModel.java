package com.juntai.upcodesafe.base.sendcode;

import android.text.TextUtils;


import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.utils.RuleTools;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Author:wang_sir
 * Time:2018/7/16 18:18
 * Description:This is SendCodeModel
 */
public class SendCodeModel  implements SendCodeContract.ISendCodeModel, IModel {


    private SendCodeContract.IUpdateView iUpdateView;
    private Disposable disposable;
    public static String CODE_WRONG = "短信验证码错误";

    public SendCodeModel(SendCodeContract.IUpdateView iUpdateView) {
        this.iUpdateView = iUpdateView;
    }

    @Override
    public void initGetTestCodeButtonStatus() {
        disposable = Observable.interval(0, 1000, TimeUnit.MILLISECONDS)
                .take(60)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (iUpdateView != null) {
                            iUpdateView.startTiming(59 - aLong);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                    }
                });
    }

    @Override
    public void receivedCheckCodeAndDispose() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

    /**
     * 检查手机号的格式
     */
    public boolean mobileFormatIsOk(String mobile) {
        if (mobile == null || TextUtils.isEmpty(mobile)) {
            if (iUpdateView != null) {
                iUpdateView.checkFormatError("手机号码不能为空");
            }

            return false;
        }
        if (!RuleTools.isMobileNO(mobile)) {
            if (iUpdateView != null) {
                iUpdateView.checkFormatError("手机号码格式不正确");
            }
            return false;
        }
        return true;

    }


    @Override
    public void onDetach() {

    }
}
