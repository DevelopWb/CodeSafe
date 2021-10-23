package com.juntai.upcodesafe.home_page.unit;

import android.os.Bundle;


import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.utils.HawkProperty;
import com.juntai.upcodesafe.utils.UserInfoManager;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  搜索添加单位
 * @date 2021/5/9 11:09
 */
public class SearchAddUnitActivity extends BaseAddUnitActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getCommitTextValue() {
        return "完成";
    }

    @Override
    protected boolean isAddInfo() {
        return false;
    }

    @Override
    protected String getHawkKey() {
        return HawkProperty.ADD_UNIT_KEY+unitId+ UserInfoManager.getUserId();
    }

    @Override
    protected void commit(MultipartBody.Builder builder) {
        if (bean != null) {
            builder.addFormDataPart("unitId",String.valueOf(bean.getId()));
        }
        mPresenter.ddUnit(builder.build(), AppHttpPath.ADD_UNIT);
    }


    @Override
    protected String getTitleName() {
        return ADD_UNIT;
    }
}
