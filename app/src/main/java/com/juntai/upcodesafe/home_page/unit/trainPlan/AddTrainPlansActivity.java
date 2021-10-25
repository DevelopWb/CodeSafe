package com.juntai.upcodesafe.home_page.unit.trainPlan;

import android.content.DialogInterface;

import com.juntai.disabled.basecomponent.utils.DialogUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.bean.TrainPlanListBean;
import com.juntai.upcodesafe.home_page.baseinspect.BaseCommitFootViewActivity;
import com.juntai.upcodesafe.utils.HawkProperty;
import com.orhanobut.hawk.Hawk;

import java.util.List;

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
        adapter.setNewData(mPresenter.getTrainAddData(null));
        List<TrainPlanListBean.DataBean> list  = Hawk.get(HawkProperty.ADD_TRAIN);
        if (list !=null&&!list.isEmpty()) {
            setAlertDialogHeightWidth(DialogUtil.getDialog(mContext).setMessage("您上次还有未提交的草稿,是否进入草稿？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.setNewData(mPresenter.getTrainAddData(list));
                        }
                    }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).show(), -1, 0);
        }

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
        //保存草稿
        if (getBaseAdapterData(true) != null) {
            List<TrainPlanListBean.DataBean> list = getBaseAdapterData(true).getTrainDesPics();
            Hawk.put(HawkProperty.ADD_TRAIN, list);
            ToastUtils.toast(mContext, "草稿保存成功");
            finish();
        }
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag,o);
        switch (tag) {
            case AppHttpPath.ADD_TRAIN_PLAN:
                if (Hawk.contains(HawkProperty.ADD_TRAIN)) {
                    Hawk.delete(HawkProperty.ADD_TRAIN);
                }
                ToastUtils.toast(mContext,"添加成功");
                finish();
                break;
            default:
                break;
        }

    }
}
