package com.juntai.upcodesafe.home_page.inspect.inspect;


import android.content.Intent;

import com.juntai.upcodesafe.bean.UnitDetailBean;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectContract;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.upcodesafe.home_page.unit.EditUnitInfoActivity;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  单位详情 更多
 * @date 2021/5/18 14:39
 */
public class UnitInfoMoreActivity extends BaseInspectionActivity implements BaseInspectContract.IInspectView {
    private UnitDetailBean.DataBean dataBean;
    @Override
    public void initData() {
        adapter.setNewData(mPresenter.getUnitInfoMoreData(dataBean,true));
    }

    @Override
    protected String getTitleName() {
        return "单位详情-更多";
    }



    @Override
    public void initView() {
        isDetail = true;
        super.initView();
        mCommitTv.setText("申请修改");
        if (getIntent() != null) {
            dataBean = getIntent().getParcelableExtra(PARCELABLE_KEY);
        }
    }

    @Override
    protected void commitLogic(MultipartBody.Builder builder) {
        //申请修改
        startActivity(new Intent(mContext, EditUnitInfoActivity.class).putExtra(PARCELABLE_KEY,dataBean));
    }
}
