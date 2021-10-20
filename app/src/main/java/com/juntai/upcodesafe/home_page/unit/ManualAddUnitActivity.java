package com.juntai.upcodesafe.home_page.unit;


import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.utils.HawkProperty;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  手动添加单位  manual
 * @date 2021/5/9 11:44
 */

public class ManualAddUnitActivity extends BaseAddUnitActivity {

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
        return HawkProperty.ADD_UNIT_KEY;
    }

    @Override
    protected void commit(MultipartBody.Builder builder) {
        mPresenter.ddUnit(builder.build(), AppHttpPath.ADD_UNIT);
    }

    @Override
    protected String getTitleName() {
        return ADD_UNIT;
    }

}
