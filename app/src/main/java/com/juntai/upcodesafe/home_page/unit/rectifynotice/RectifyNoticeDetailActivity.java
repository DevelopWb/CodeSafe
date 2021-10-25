package com.juntai.upcodesafe.home_page.unit.rectifynotice;

import android.os.Bundle;
import android.view.View;

import com.juntai.upcodesafe.bean.RectifyNoticeDeatilBean;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectionActivity;

/**
 * @aouther tobato
 * @description 描述  整改通知书详情
 * @date 2021-10-17 16:57
 */
public class RectifyNoticeDetailActivity extends BaseInspectionActivity {

    @Override
    public void initData() {
        if (getIntent() != null) {
            String noticeId = getIntent().getStringExtra(BASE_ID);
            mPresenter.getRectifyNoticeDetail(getBaseBuilder().add("id",noticeId).build(),"");
        }

    }

    @Override
    protected View getFootView() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleName() {
        return "整改指令书";
    }

    @Override
    public void onSuccess(String tag, Object o) {
        if (o!=null) {
            RectifyNoticeDeatilBean.DataBean dataBean = (RectifyNoticeDeatilBean.DataBean) o;

            adapter.setNewData(mPresenter.getRectifyNoticeDetailData(dataBean));
        }


    }
}
