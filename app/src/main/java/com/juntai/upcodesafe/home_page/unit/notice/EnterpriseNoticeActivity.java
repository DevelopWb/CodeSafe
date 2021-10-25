package com.juntai.upcodesafe.home_page.unit.notice;

import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.BaseWebviewActivity;
import com.juntai.upcodesafe.bean.NoticeBean;
import com.juntai.upcodesafe.base.BaseSearchAndListActivity;

/**
 * @aouther tobato
 * @description 描述  企业里面的通知公告
 * @date 2021-10-09 11:29
 */
public class EnterpriseNoticeActivity extends BaseSearchAndListActivity {


    @Override
    public void initData() {
        super.initData();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NoticeBean.DataBean dataBean = (NoticeBean.DataBean) adapter.getData().get(position);
                BaseWebviewActivity.startBaseWebviewActivity(mContext, BaseWebviewActivity.class, BaseWebviewActivity.WEB_CONTENT, dataBean.getContent(), "通知详情");

            }
        });
    }

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
