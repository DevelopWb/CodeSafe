package com.juntai.upcodesafe.home_page.homepage_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.HomeBusinessBean;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectContract;
import com.juntai.upcodesafe.home_page.unit.industryDetail.IndustryInfoActivity;
import com.juntai.upcodesafe.utils.UserInfoManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  属地或者网格权限的布局
 * @CreateDate: 2021-10-18 11:21
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-18 11:21
 */
public class HomepageOtherSupervisionFragment extends HomePageBaseSupervisionFragment {
    private RecyclerView mRecyclerview;
    private SmartRefreshLayout mSmartrefreshlayout;
    private HomePageSupervisionMenuAdapter adapter;

    @Override
    protected View getChildFragmentView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycleview_layout, null);
        mRecyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        mSmartrefreshlayout = (SmartRefreshLayout) view.findViewById(R.id.smartrefreshlayout);
        mSmartrefreshlayout.setEnableLoadMore(false);
        mSmartrefreshlayout.setEnableRefresh(false);
        adapter = new HomePageSupervisionMenuAdapter(R.layout.homepage_supervision_menu);
        getBaseActivity().initRecyclerviewGridLayout(mRecyclerview, adapter,3);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeBusinessBean.DataBean dataBean = (HomeBusinessBean.DataBean) adapter.getData().get(position);
                startActivity(new Intent(mContext, IndustryInfoActivity.class)
                        .putExtra(IndustryInfoActivity.BASE_ID,dataBean.getId()));
            }
        });
        return view;
    }

    @Override
    protected void initData() {
        int accountType = UserInfoManager.getAccountTypeId();
        switch (accountType) {
            case 2:
                mPresenter.getHomePageBusinessTerritory(getBaseBuilder()
                        .add("territoryId", String.valueOf(UserInfoManager.getTerritoryId())).build(), AppHttpPath.GET_BUSINESS_DIRECTOR);

                break;
            case 3:
                mPresenter.getHomePageBusinessGrid(getBaseBuilder()
                        .add("gridId", String.valueOf(UserInfoManager.getGridId())).build(), AppHttpPath.GET_BUSINESS_DIRECTOR);

                break;
        }
    }
    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        HomeBusinessBean homeBusinessBean = (HomeBusinessBean) o;
        if (homeBusinessBean != null) {
            List<HomeBusinessBean.DataBean> arrays = homeBusinessBean.getData();
            adapter.setNewData(arrays);
        }


    }

    @Override
    protected void refreshData() {
        initData();
    }

}
