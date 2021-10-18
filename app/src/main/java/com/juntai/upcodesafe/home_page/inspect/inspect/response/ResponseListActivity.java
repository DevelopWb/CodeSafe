package com.juntai.upcodesafe.home_page.inspect.inspect.response;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.BaseRecyclerviewActivity;
import com.juntai.upcodesafe.base.BaseWebviewActivity;
import com.juntai.upcodesafe.bean.IdNameBean;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectContract;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectPresent;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectionInfoActivity;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述  责任清单
 * @date 2021-10-14 8:43
 */
public class ResponseListActivity extends BaseRecyclerviewActivity<BaseInspectPresent> implements BaseInspectContract.IInspectView {

    @Override
    protected BaseInspectPresent createPresenter() {
        return new BaseInspectPresent();
    }

    @Override
    public void initData() {
        setTitleName(BaseInspectContract.RESPONSE_LIST);
        mSmartrefreshlayout.setEnableLoadMore(false);
        if (getIntent() != null) {
            int unitType = getIntent().getIntExtra(BaseInspectionInfoActivity.BASE_ID, 0);
            mPresenter.getResponseList(getBaseBuilder().add("unitType", String.valueOf(unitType)).build(), AppHttpPath.GET_RESPONSE_LIST);
        }
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BaseWebviewActivity.startBaseWebviewActivity(mContext,BaseWebviewActivity.class,getString(R.string.user_xieyi_url),"");

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void freshlayoutOnLoadMore() {

    }

    @Override
    protected void freshlayoutOnRefresh() {

    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new ResponseListAdapter(R.layout.notice_item);
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);

        switch (tag) {
            case AppHttpPath.GET_RESPONSE_LIST:
                IdNameBean idNameBean = (IdNameBean) o;
                List<IdNameBean.DataBean> arrays = idNameBean.getData();
                adapter.setNewData(arrays);
                break;
            default:
                break;
        }
    }
}
