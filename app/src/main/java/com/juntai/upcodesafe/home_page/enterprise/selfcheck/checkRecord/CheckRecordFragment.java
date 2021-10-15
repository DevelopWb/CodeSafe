package com.juntai.upcodesafe.home_page.enterprise.selfcheck.checkRecord;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.BaseRecyclerviewFragment;
import com.juntai.upcodesafe.bean.CheckRecordBean;
import com.juntai.upcodesafe.bean.UnitDetailBean;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectContract;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectPresent;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.upcodesafe.home_page.enterprise.selfcheck.checkRecord.checkRecordInfo.CheckRecordDetailActivity;
import com.juntai.upcodesafe.utils.HawkProperty;
import com.orhanobut.hawk.Hawk;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述   工作记录
 * @date 2021/6/1 15:58
 */
public class CheckRecordFragment extends BaseRecyclerviewFragment<BaseInspectPresent> implements BaseInspectContract.IInspectView {

    private String fragmentTag;
    private UnitDetailBean.DataBean unitBean;


    @Override
    protected void lazyLoad() {
        fragmentTag = getArguments().getString(BaseInspectContract.TAB_TITLES);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CheckRecordBean.DataBean checkRecordBean = (CheckRecordBean.DataBean) adapter.getData().get(position);
                startActivity(new Intent(mContext, CheckRecordDetailActivity.class).putExtra(BaseInspectionActivity.BASE_ID,checkRecordBean.getId()));


            }
        });
        mSmartrefreshlayout.setEnableLoadMore(false);
        getAdapterData();
    }

    @Override
    protected BaseInspectPresent createPresenter() {
        return new BaseInspectPresent();
    }

    public static CheckRecordFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(BaseInspectContract.TAB_TITLES, type);
        CheckRecordFragment fragment = new CheckRecordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        unitBean = Hawk.get(HawkProperty.UNIT_KEY);
    }

    @Override
    protected void freshlayoutOnLoadMore() {
        getAdapterData();
    }

    /**
     * 获取数据
     */
    private void getAdapterData() {

        mPresenter.getCheckList(getBaseAppActivity().getBaseBuilder()
                .add("unitId", String.valueOf(unitBean.getId()))
                .add("checkType", String.valueOf(getStatus())).build(), "");
    }

    @Override
    protected void freshlayoutOnRefresh() {
        getAdapterData();
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new CheckRecordAdapter(R.layout.check_record_item);
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        CheckRecordBean checkRecordBean = (CheckRecordBean) o;
        if (checkRecordBean != null) {
            List<CheckRecordBean.DataBean> arrays = checkRecordBean.getData();
            adapter.setNewData(arrays);
        }

    }

    /**
     * 获取状态检查类型
     * 1企业自查；2监管检查；3属地检查；4网格检查
     *
     * @return
     */
    private int getStatus() {

        int status = 0;
        switch (fragmentTag) {
            case BaseInspectContract.TAB_ALL:
                status = 0;
                break;
            case BaseInspectContract.TAB_CHECK_SELF:
                status = 1;
                break;
            case BaseInspectContract.TAB_SUPERVISE_CHECK:
                status = 2;
                break;
            case BaseInspectContract.TAB_TERRITORY_SUPERVISE_CHECK:
                status = 3;
                break;
            case BaseInspectContract.TAB_GRID_SUPERVISE_CHECK:
                status = 4;
                break;
            default:
                break;
        }
        return status;
    }
}
