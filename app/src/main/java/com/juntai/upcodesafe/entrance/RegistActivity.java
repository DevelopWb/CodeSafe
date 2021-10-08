package com.juntai.upcodesafe.entrance;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.sendcode.SmsCheckCodeActivity;

public class RegistActivity extends SmsCheckCodeActivity implements View.OnClickListener {

    /**
     * 注册
     */
    private TextView mRegistTv;
    private ImageView mCloseIv;
    /**
     * 注册手机号
     */
    private EditText mRegistPhoneEt;
    /**
     * 短信验证码
     */
    private EditText mRegistCheckCodeEt;
    /**
     * 获取验证码
     */
    private TextView mRegistSendCheckCodeTv;
    /**
     * 密码
     */
    private EditText mPasswordEt;
    private ImageView mHideShowIv;
    private boolean isHide = true;//默认隐藏

    @Override
    public int getLayoutView() {
        return R.layout.activity_regist;
    }

    @Override
    public void initView() {
        initToolbarAndStatusBar(false);
        mBaseRootCol.setFitsSystemWindows(true);
        mImmersionBar.statusBarColor(com.juntai.disabled.basecomponent.R.color.white)
                .statusBarDarkFont(true)
                .init();
        mRegistTv = (TextView) findViewById(R.id.regist_tv);
        mRegistTv.setOnClickListener(this);
        mCloseIv = (ImageView) findViewById(R.id.close_iv);
        mCloseIv.setOnClickListener(this);
        mRegistPhoneEt = (EditText) findViewById(R.id.regist_phone_et);
        mRegistCheckCodeEt = (EditText) findViewById(R.id.regist_check_code_et);
        mRegistSendCheckCodeTv = (TextView) findViewById(R.id.regist_send_check_code_tv);
        mRegistSendCheckCodeTv.setOnClickListener(this);
        mPasswordEt = (EditText) findViewById(R.id.password_et);
        mHideShowIv = (ImageView) findViewById(R.id.hide_show_iv);
        mHideShowIv.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regist_tv:
                // TODO: 2021-10-08 调用注册的接口  成功后跳转到登录界面
                startActivity(new Intent(mContext, LoginActivity.class));
                break;
            default:
                break;
            case R.id.close_iv:
                finish();
                break;
            case R.id.regist_send_check_code_tv:
                // TODO: 2021-10-08 注册界面发送验证码
                break;
            case R.id.hide_show_iv:
                if (isHide) {
                    isHide = false;
                    //设置EditText的密码为可见的
                    mPasswordEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    isHide = true;
                    //设置EditText的密码为隐藏
                    mPasswordEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                mPasswordEt.setSelection(mPasswordEt.getText().length());
                break;
        }
    }

    @Override
    protected TextView getSendCodeTv() {
        return null;
    }

}
