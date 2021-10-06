package com.juntai.upcodesafe.entrance;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.base.BaseMvpActivity;
import com.juntai.upcodesafe.MainActivity;
import com.juntai.upcodesafe.R;

/**
 * @aouther tobato
 * @description 描述  登录
 * @date 2020/3/6 9:12
 */
public class LoginActivity extends BaseMvpActivity<EntrancePresent> implements EntranceContract.IEntranceView, View.OnClickListener {


    /**
     * 账号
     */
    private EditText mRegistPhoneEt;
    /**
     * 密码
     */
    private EditText mPassword;
    /**
     * 登录
     */
    private TextView mLoginTv;
    /**
     * 忘记密码
     */
    private TextView mForgetPwdTv;
    /**
     * 注册
     */
    private TextView mRegistTv;

    @Override
    public int getLayoutView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public void initData() {
    }


    @Override
    protected EntrancePresent createPresenter() {
        return new EntrancePresent();
    }

    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.login_tv:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.close_iv:
                finish();
                break;
            case R.id.regist_tv:
                startActivity(new Intent(this, RegistActivity.class));
                break;
            case R.id.forget_pwd_tv:
                break;
        }
    }


    @Override
    public void initView() {
        initToolbarAndStatusBar(false);
        mRegistPhoneEt = (EditText) findViewById(R.id.regist_phone_et);
        mPassword = (EditText) findViewById(R.id.password);
        mLoginTv = (TextView) findViewById(R.id.login_tv);
        mLoginTv.setOnClickListener(this);
        mForgetPwdTv = (TextView) findViewById(R.id.forget_pwd_tv);
        mForgetPwdTv.setOnClickListener(this);
        mRegistTv = (TextView) findViewById(R.id.regist_tv);
        mRegistTv.setOnClickListener(this);
    }

}
