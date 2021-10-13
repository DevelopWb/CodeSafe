package com.juntai.upcodesafe.home_page.enterprise.accidentWarn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.home_page.enterprise.BaseSearchAndListActivity;
import com.juntai.upcodesafe.home_page.enterprise.notice.EnterpriseNoticeAdapter;

/**
 * @aouther tobato
 * @description 描述  事故警示
 * @date 2021-10-09 11:59
 */
public class AccidentWarnActivity extends BaseSearchAndListActivity {



    @Override
    protected void startSearch(String s) {
        if (TextUtils.isEmpty(s)) {
            mPresenter.getHomePageAccident(getBaseBuilder().build(), AppHttpPath.HOMEPAGE_NOTICE);
        }else {
            mPresenter.getHomePageAccident(getBaseBuilder().add("keyword",s).build(), AppHttpPath.HOMEPAGE_NOTICE);
        }
    }

    @Override
    protected String getTitleName() {
        return "事故警示";
    }


    @Override
    protected BaseQuickAdapter getAdapter() {
        return new EnterpriseNoticeAdapter(R.layout.enterprise_notice_item);
    }
}
