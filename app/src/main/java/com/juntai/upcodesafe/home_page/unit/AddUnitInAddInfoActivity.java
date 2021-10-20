package com.juntai.upcodesafe.home_page.unit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.utils.HawkProperty;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  补充资料的时候 手动添加企业
 * @date 2021-10-19 16:20
 */
public class AddUnitInAddInfoActivity extends BaseAddUnitActivity {

    @Override
    protected String getCommitTextValue() {
        return "完成";
    }

    @Override
    protected boolean isAddInfo() {
        return true;
    }

    @Override
    protected String getHawkKey() {
        return HawkProperty.ADD_UNIT_KEY;
    }

    @Override
    protected void commit(MultipartBody.Builder builder) {
        //补充资料时添加单位
        mPresenter.manualAddUnit(builder.build(), AppHttpPath.MANUAL_ADD_UNIT);
    }

    @Override
    protected String getTitleName() {
        return ADD_UNIT;
    }

}
