package com.juntai.upcodesafe.home_page.unit.trainPlan;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.upcodesafe.AppHttpPath;
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
        mPresenter.addTrainPlans(builder.build(), AppHttpPath.ADD_TRAIN_PLAN);
    }

    @Override
    protected void saveDraft() {
        // TODO: 2021-10-23 培训计划 保存草稿
//        //保存草稿
//        if (getBaseAdapterData(true) != null) {
//            Hawk.put(getHawkKey(), getBaseAdapterData(true).getUnitDataBean());
//            ToastUtils.toast(mContext, "草稿保存成功");
//            finish();
//        }
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag,o);
        switch (tag) {
            case AppHttpPath.ADD_TRAIN_PLAN:
                ToastUtils.toast(mContext,"添加成功");
                finish();
                break;
            default:
                break;
        }

    }
}
