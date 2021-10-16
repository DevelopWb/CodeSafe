package com.juntai.upcodesafe.home_page.inspect.notice;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.home_page.inspect.BaseSearchAndListActivity;

/**
 * @aouther tobato
 * @description 描述  企业里面的通知公告
 * @date 2021-10-09 11:29
 */
public class EnterpriseNoticeActivity extends BaseSearchAndListActivity {




    @Override
    protected BaseQuickAdapter getAdapter() {
        return new EnterpriseNoticeAdapter(R.layout.notice_item);
    }

    @Override
    protected void startSearch(String s) {
        if (TextUtils.isEmpty(s)) {
            mPresenter.getHomePageNotice(getBaseBuilder().build(), AppHttpPath.HOMEPAGE_NOTICE);
        }else {
            mPresenter.getHomePageNotice(getBaseBuilder().add("keyword",s).build(), AppHttpPath.HOMEPAGE_NOTICE);
        }

    }

    @Override
    protected String getTitleName() {
        return "通知公告";
    }


}
