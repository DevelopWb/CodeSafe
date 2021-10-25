package com.juntai.upcodesafe.home_page.unit.checkRecord.checkRecordInfo;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectContract;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectionActivity;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述 添加整改通知书
 * @date 2021/5/28 9:58
 */
public class AddRectifyNoticeActivity extends BaseInspectionActivity implements BaseInspectContract.IInspectView {

    private int recordId;
    private int unitId;

    @Override
    public void initData() {
        mCommitTv.setText("提交");
        adapter.setNewData(mPresenter.getRectifyNoticeInfo());
        recordId = getIntent().getIntExtra(BASE_ID2,0);
        unitId = getIntent().getIntExtra(BASE_ID,0);
    }


    @Override
    protected String getTitleName() {
        return "添加整改指令书";
    }

    @Override
    protected void commitLogic(MultipartBody.Builder builder) {
        mPresenter.addRectifyNotice(builder.addFormDataPart("unitId",String.valueOf(unitId)).addFormDataPart("recordId",
                String.valueOf(recordId)).build(),"");
    }

    @Override
    public void onSuccess(String tag, Object o) {
        ToastUtils.toast(mContext,"提交成功");
        finish();
    }
}
