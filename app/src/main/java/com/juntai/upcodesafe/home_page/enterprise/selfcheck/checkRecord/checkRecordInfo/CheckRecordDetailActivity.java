package com.juntai.upcodesafe.home_page.enterprise.selfcheck.checkRecord.checkRecordInfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.bean.CheckDetailBean;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectionActivity;

/**
 * @aouther tobato
 * @description 描述  检查记录详情
 * @date 2021-10-15 15:51
 */
public class CheckRecordDetailActivity extends BaseInspectionActivity {

    @Override
    public void initData() {
        mPresenter.getCheckRecordDetail(getBaseBuilder().add("recordId",String.valueOf(baseId)).build(), AppHttpPath.CHECK_RECORD_DETAIL);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        idDetail = true;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleName() {
        return "检查详情";
    }

    @Override
    protected View getFootView() {
        return null;
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        switch (tag) {
            case AppHttpPath.CHECK_RECORD_DETAIL:
                CheckDetailBean checkDetailBean = (CheckDetailBean) o;
                if (checkDetailBean != null) {
                    CheckDetailBean.DataBean dataBean =  checkDetailBean.getData();
                    adapter.setNewData(mPresenter.getCheckedDetailData(dataBean));
                }

                break;
            default:
                break;
        }

    }
}
