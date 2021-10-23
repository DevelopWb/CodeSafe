package com.juntai.upcodesafe.home_page.inspect.trainPlan;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.upcodesafe.home_page.baseinspect.BaseCommitFootViewActivity;
import com.juntai.upcodesafe.utils.HawkProperty;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述 添加培训计划
 * @date 2021-10-16 9:43
 */
public class AddTrainPlansActivity extends BaseCommitFootViewActivity {

    @Override
    public void initData() {
        HawkProperty.UPLOAD_TYPE =  2;
        adapter.setNewData(mPresenter.addDesPicLayout("培训计划描述","培训计划照片或扫描件",0));
    }




    @Override
    protected String getTitleName() {
        return "添加培训计划";
    }

    @Override
    protected String getCommitTextValue() {
        return "提交";
    }

    @Override
    protected void commitRequest(MultipartBody.Builder builder) {
        mPresenter.addTrainPlans(builder.build(),"");
    }

    @Override
    protected void saveDraft() {

    }

    @Override
    public void onSuccess(String tag, Object o) {
        ToastUtils.toast(mContext,"添加成功");
        finish();
    }
}
