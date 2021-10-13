package com.juntai.upcodesafe.entrance;

import android.view.View;

import com.juntai.disabled.basecomponent.utils.MD5;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.base.BaseWithSmsActivity;

/**
 * @aouther tobato
 * @description 描述  注册
 * @date 2021-10-13 14:49
 */
public class RegistActivity extends BaseWithSmsActivity implements View.OnClickListener {
    @Override
    protected String getPwdHint() {
        return "请输入你的密码";
    }

    @Override
    protected String getCommitTextName() {
        return "注册";
    }

    @Override
    protected String getTitleName() {
        return "注册";
    }

    @Override
    protected void commit() {
        mPresenter.regist(getTextViewValue(mRegistPhoneEt), MD5.md5(String.format("%s#%s", getTextViewValue(mRegistPhoneEt), getTextViewValue(mPasswordEt)))
                ,getTextViewValue(mRegistCheckCodeEt), AppHttpPath.REGIST);
    }
}
