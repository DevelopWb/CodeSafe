package com.juntai.upcodesafe.home_page.unit;


import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.utils.HawkProperty;
import com.juntai.upcodesafe.utils.UserInfoManager;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  修改单位详情
 * @date 2021/5/18 15:31
 */

public class EditUnitInfoActivity extends BaseAddUnitActivity {

    @Override
    protected String getTitleName() {
        return "编辑单位详情";
    }


    @Override
    protected String getCommitTextValue() {
        return "提交审核";
    }

    @Override
    protected boolean isAddInfo() {
        return false;
    }

    @Override
    protected String getHawkKey() {
        return HawkProperty.EDIT_UNIT_KEY + unitId+ UserInfoManager.getUserId();
    }

    @Override
    protected void commit(MultipartBody.Builder builder) {
        mPresenter.editUnitInfo( builder.build(), AppHttpPath.EDIT_UNIT_INFO);

    }
}
