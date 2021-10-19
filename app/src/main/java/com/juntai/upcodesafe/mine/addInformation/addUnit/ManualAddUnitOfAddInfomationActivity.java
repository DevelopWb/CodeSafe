package com.juntai.upcodesafe.mine.addInformation.addUnit;

import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.utils.HawkProperty;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  补充信息中的手动添加单位
 * @date 2021/5/9 11:44
 */

public class ManualAddUnitOfAddInfomationActivity extends BaseAddUnitOfAddInfomationActivity {

    @Override
    protected String getCommitTextValue() {
        return "完成";
    }

    @Override
    protected String getHawkKey() {
        return HawkProperty.ADD_UNIT_KEY;
    }

    @Override
    protected void commit(MultipartBody.Builder builder) {
        mPresenter.manualAddUnit(builder.build(), AppHttpPath.MANUAL_ADD_UNIT);
    }

    @Override
    protected String getTitleName() {
        return "添加企业";
    }

}
