package com.juntai.upcodesafe.base;

import android.content.Intent;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.utils.MD5;
import com.juntai.disabled.basecomponent.utils.RuleTools;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.sendcode.SmsCheckCodeActivity;
import com.juntai.upcodesafe.entrance.LoginActivity;

/**
 * @aouther tobato
 * @description 描述
 * @date 2021-10-13 14:49
 */
public  abstract class BaseWithSmsActivity extends SmsCheckCodeActivity implements View.OnClickListener {

    /**
     * 注册
     */
    public TextView mRegistTv;
    /**
     * 注册手机号
     */
    public EditText mRegistPhoneEt;
    /**
     * 短信验证码
     */
    public EditText mRegistCheckCodeEt;
    /**
     * 获取验证码
     */
    public TextView mRegistSendCheckCodeTv;
    /**
     * 密码
     */
    public EditText mPasswordEt;
    public ImageView mHideShowIv;
    public boolean isHide = true;//默认隐藏

    @Override
    public int getLayoutView() {
        return R.layout.activity_regist;
    }

    @Override
    public void initView() {
        setTitleName(getTitleName());
        mRegistTv = (TextView) findViewById(R.id.regist_tv);
        mRegistTv.setText(getCommitTextName());
        mRegistTv.setOnClickListener(this);
        mRegistPhoneEt = (EditText) findViewById(R.id.regist_phone_et);
        mRegistCheckCodeEt = (EditText) findViewById(R.id.regist_check_code_et);
        mRegistSendCheckCodeTv = (TextView) findViewById(R.id.regist_send_check_code_tv);
        mRegistSendCheckCodeTv.setOnClickListener(this);
        mPasswordEt = (EditText) findViewById(R.id.password_et);
        mPasswordEt.setHint(getPwdHint());
        mHideShowIv = (ImageView) findViewById(R.id.hide_show_iv);
        mHideShowIv.setOnClickListener(this);
    }

    protected abstract String getPwdHint();

    protected abstract String getCommitTextName();

    protected abstract String getTitleName();

    @Override
    public void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regist_tv:
                // 调用注册的接口  成功后跳转到登录界面
                if (!RuleTools.isMobileNO(getTextViewValue(mRegistPhoneEt))) {
                    ToastUtils.error(mContext, "手机号码格式不正确");
                    return;
                }
                if (TextUtils.isEmpty(getTextViewValue(mRegistCheckCodeEt))) {
                    ToastUtils.error(mContext, "请输入验证码");
                    return;
                }
                if (TextUtils.isEmpty(getTextViewValue(mPasswordEt))) {
                    ToastUtils.error(mContext, "请输入密码");
                    return;
                }

                commit();

                break;
            default:
                break;
            case R.id.regist_send_check_code_tv:
                // 注册界面发送验证码
                if (!RuleTools.isMobileNO(getTextViewValue(mRegistPhoneEt))) {
                    ToastUtils.error(mContext, "手机号码格式不正确");
                    return;
                }
                sendCheckCode(getTextViewValue(mRegistPhoneEt));
                break;
            case R.id.hide_show_iv:
                if (isHide) {
                    isHide = false;
                    //设置EditText的密码为可见的
                    mHideShowIv.setImageResource(R.mipmap.show_icon);
                    mPasswordEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    isHide = true;
                    //设置EditText的密码为隐藏
                    mHideShowIv.setImageResource(R.mipmap.hide_icon);
                    mPasswordEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                mPasswordEt.setSelection(mPasswordEt.getText().length());
                break;
        }
    }

    protected abstract void commit();

    @Override
    protected TextView getSendCodeTv() {
        return mRegistSendCheckCodeTv;
    }


    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        switch (tag) {
            case AppHttpPath.REGIST:
                ToastUtils.toast(mContext,getTitleName()+"成功");
                finish();
                break;
            default:

                break;
        }
    }
}
