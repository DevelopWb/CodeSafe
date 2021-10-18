package com.juntai.upcodesafe.home_page.inspect.inspect.rectifynotice;

import android.content.Intent;
import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.RectifyNoticeListBean;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.upcodesafe.home_page.baseinspect.BaseRecordActivity;

/**
 * @aouther tobato
 * @description 描述  整改通知书列表
 * @date 2021/5/28 15:50
 */
public class RectifyNoticeListActivity extends BaseRecordActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleName() {
        return "整改通知书列表";
    }

    @Override
    protected void requestHisData() {
        mPresenter.getRectifyNoticeList(getBaseBuilder().add("unitId", String.valueOf(id)).build(),
                AppHttpPath.GET_RECTIFY_NOTICE_LIST);
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new RectifyNoticeListAdapter(R.layout.item_record);
    }

    @Override
    protected void onAdapterItemClick(BaseQuickAdapter adapter, int position) {
        RectifyNoticeListBean.DataBean datasBean = (RectifyNoticeListBean.DataBean) adapter.getData().get(position);
        startActivity(new Intent(mContext,RectifyNoticeDetailActivity.class)
                .putExtra(BaseInspectionActivity.BASE_ID,
                String.valueOf(datasBean.getId())));

    }
}
