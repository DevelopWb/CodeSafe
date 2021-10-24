package com.juntai.upcodesafe.home_page.inspect.trainPlan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.juntai.upcodesafe.bean.TrainPlanListBean;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectionActivity;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述  培训计划
 * @date 2021-10-16 8:52
 */
public class TrainPlanActivity extends BaseInspectionActivity {

    @Override
    public void initData() {
        getTitleRightTv().setText("添加");
        getTitleRightTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加培训计划
                startActivity(new Intent(mContext,AddTrainPlansActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getTrainPlanList(getBaseBuilder().add("unitId", String.valueOf(baseId)).build(), "");
    }

    @Override
    protected View getFootView() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isDetail = true;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleName() {
        return "培训计划";
    }

    @Override
    public void onSuccess(String tag, Object o) {
        if (o != null) {
            List<TrainPlanListBean.DataBean> dataBeans = (List<TrainPlanListBean.DataBean>) o;
            adapter.setNewData(mPresenter.getTrainPlansData(dataBeans));
        }

    }
}
