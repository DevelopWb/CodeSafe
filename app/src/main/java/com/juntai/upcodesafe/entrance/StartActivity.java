package com.juntai.upcodesafe.entrance;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.juntai.upcodesafe.MainActivity;
import com.juntai.upcodesafe.utils.HawkProperty;
import com.juntai.upcodesafe.utils.UserInfoManager;
import com.orhanobut.hawk.Hawk;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * @aouther Ma
 * @date 2019/3/13
 */
public class StartActivity extends RxAppCompatActivity {
    String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION};

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new RxPermissions(this)
                .request(permissions)
                .delay(1, TimeUnit.SECONDS)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            //所有权限通过
                        } else {
                            //有一个权限没通过
                        }
                        if (UserInfoManager.isLogin()) {
                            startActivity(new Intent(StartActivity.this, MainActivity.class));
                        }else {
                            startActivity(new Intent(StartActivity.this, LoginActivity.class));
                        }

                        finish();
//                        if (SPTools.getBoolean(StartActivity.this, "first_start", true)) {
//                            startActivity(new Intent(StartActivity.this, GuideActivity.class));
//                            finish();
//                        } else {
//                            startActivity(new Intent(StartActivity.this, MainActivity.class));
//                            finish();
//                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
    }
}
