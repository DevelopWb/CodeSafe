package com.juntai.upcodesafe.entrance;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.sendcode.SmsCheckCodeActivity;

public class RegistActivity extends SmsCheckCodeActivity implements View.OnClickListener {

    /**
     * 注册
     */
    private TextView mRegistTv;

    @Override
    public int getLayoutView() {
        return R.layout.activity_regist;
    }

    @Override
    public void initView() {
        initToolbarAndStatusBar(false);
        mRegistTv = (TextView) findViewById(R.id.regist_tv);
        mRegistTv.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regist_tv:
                startActivity(new Intent(mContext,LoginActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected TextView getSendCodeTv() {
        return null;
    }

}
