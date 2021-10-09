package com.juntai.upcodesafe.home_page.enterprise.notice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.home_page.enterprise.BaseSearchAndListActivity;

/**
 * @aouther tobato
 * @description 描述  企业里面的通知公告
 * @date 2021-10-09 11:29
 */
public class EnterpriseNoticeActivity extends BaseSearchAndListActivity {


    @Override
    protected void freshlayoutOnLoadMore() {

    }

    @Override
    protected void freshlayoutOnRefresh() {

    }


    @Override
    protected BaseQuickAdapter getAdapter() {
        return new EnterpriseNoticeAdapter(R.layout.enterprise_notice_item);
    }

    @Override
    protected String getTitleName() {
        return "通知公告";
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
