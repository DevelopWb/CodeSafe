package com.juntai.upcodesafe.entrance;


import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.base.BaseMvpActivity;
import com.juntai.disabled.basecomponent.utils.ActivityManagerTool;
import com.juntai.disabled.basecomponent.utils.MD5;
import com.juntai.disabled.basecomponent.utils.RuleTools;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.upcodesafe.MainActivity;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.UserBean;
import com.juntai.upcodesafe.utils.HawkProperty;
import com.orhanobut.hawk.Hawk;

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
    private ImageView mHideShowIv;

    private boolean isHide = true;//默认隐藏

    @Override
    public int getLayoutView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public void initView() {
        initToolbarAndStatusBar(false);
        mBaseRootCol.setFitsSystemWindows(true);
        mImmersionBar.statusBarColor(com.juntai.disabled.basecomponent.R.color.white)
                .statusBarDarkFont(true)
                .init();
        mRegistPhoneEt = (EditText) findViewById(R.id.regist_phone_et);
        mPassword = (EditText) findViewById(R.id.password_et);
        mPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        mLoginTv = (TextView) findViewById(R.id.login_tv);
        mLoginTv.setOnClickListener(this);
        mForgetPwdTv = (TextView) findViewById(R.id.forget_pwd_tv);
        mForgetPwdTv.setOnClickListener(this);
        mRegistTv = (TextView) findViewById(R.id.regist_tv);
        mRegistTv.setOnClickListener(this);
        mHideShowIv = (ImageView) findViewById(R.id.hide_show_iv);
        mHideShowIv.setOnClickListener(this);
        ImageView mCloseIv = (ImageView) findViewById(R.id.close_iv);
        mCloseIv.setOnClickListener(this);
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
            case EntranceContract.LOGIN_TAG:
                UserBean loginBean = (UserBean) o;
                ToastUtils.success(mContext, "登录成功");
                Hawk.put(HawkProperty.LOGIN_KEY, loginBean);
                Hawk.put(HawkProperty.TOKEN_KEY, loginBean.getData().getToken());
                startActivity(new Intent(mContext, MainActivity.class));
//                EventManager.sendStringMsg(ActionConfig.BROAD_LOGIN_AFTER);
                onBackPressed();
                break;
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
                // 调用登录的接口
                String account = mRegistPhoneEt.getText().toString();
                String password = mPassword.getText().toString();
                if (!RuleTools.isMobileNO(account)) {
                    ToastUtils.error(mContext, "手机号码格式不正确");
                    return;
                }
                if (password.isEmpty()) {
                    ToastUtils.error(mContext, "登录密码不能为空");
                    return;
                }
                mPresenter.login(account, MD5.md5(String.format("%s#%s", account, password)),
                        EntranceContract.LOGIN_TAG);
                break;
            case R.id.close_iv:
               onBackPressed();
                break;
            case R.id.regist_tv:
                startActivity(new Intent(this, RegistActivity.class));
                break;
            case R.id.forget_pwd_tv:
                // TODO: 2021-10-08 跳转到忘记密码的界面  这个界面和注册差不多。。
                break;
            case R.id.hide_show_iv:
                if (isHide) {
                    isHide = false;
                    //设置EditText的密码为可见的
                    mPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    isHide = true;
                    //设置EditText的密码为隐藏
                    mPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                mPassword.setSelection(mPassword.getText().length());
                break;
        }
    }

    @Override
    public void onBackPressed() {
        ActivityManagerTool.getInstance().finishApp();
    }
}
