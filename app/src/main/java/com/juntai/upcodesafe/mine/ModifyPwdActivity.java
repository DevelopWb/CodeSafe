package com.juntai.upcodesafe.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.juntai.disabled.basecomponent.utils.MD5;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.base.BaseWithSmsActivity;

/**
 * @aouther tobato
 * @description 描述  修改密码
 * @date 2021-10-13 15:09
 */
public class ModifyPwdActivity extends BaseWithSmsActivity {

    @Override
    protected String getPwdHint() {
        return "请输入新密码";
    }

    @Override
    protected String getCommitTextName() {
        return "修改密码";
    }

    @Override
    protected String getTitleName() {
        return "修改密码";
    }

    @Override
    protected void commit() {
        mPresenter.modifyPwd(getTextViewValue(mRegistPhoneEt), MD5.md5(String.format("%s#%s", getTextViewValue(mRegistPhoneEt), getTextViewValue(mPasswordEt)))
                ,getTextViewValue(mRegistCheckCodeEt), AppHttpPath.REGIST);
    }
}
