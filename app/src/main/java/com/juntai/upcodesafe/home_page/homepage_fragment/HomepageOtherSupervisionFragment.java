package com.juntai.upcodesafe.home_page.homepage_fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.utils.UserInfoManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

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

    @Override
    protected View getChildFragmentView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycleview_layout, null);
        mRecyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        mSmartrefreshlayout = (SmartRefreshLayout) view.findViewById(R.id.smartrefreshlayout);
        mSmartrefreshlayout.setEnableLoadMore(false);
        mSmartrefreshlayout.setEnableRefresh(false);
        return view;
    }

    @Override
    protected void initData() {
        int accountType = UserInfoManager.getAccountTypeId();
        switch (accountType) {
            case 2:
                mPresenter.getHomePageBusinessDirector(getBaseBuilder()
                        .add("territoryId", String.valueOf(UserInfoManager.getTerritoryId())).build(), AppHttpPath.GET_BUSINESS_DIRECTOR);

                break;
            case 3:
                mPresenter.getHomePageBusinessDirector(getBaseBuilder()
                        .add("gridId", String.valueOf(UserInfoManager.getGridId())).build(), AppHttpPath.GET_BUSINESS_DIRECTOR);

                break;
        }
    }


}
