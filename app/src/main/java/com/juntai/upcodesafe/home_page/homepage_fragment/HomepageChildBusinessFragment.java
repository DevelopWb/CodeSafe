package com.juntai.upcodesafe.home_page.homepage_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.BaseRecyclerviewFragment;
import com.juntai.upcodesafe.bean.CheckRecordBean;
import com.juntai.upcodesafe.bean.HomeBusinessBean;
import com.juntai.upcodesafe.bean.UnitDetailBean;
import com.juntai.upcodesafe.home_page.HomePageContract;
import com.juntai.upcodesafe.home_page.HomePagePresent;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectContract;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectPresent;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.upcodesafe.home_page.inspect.inspect.checkRecord.CheckRecordAdapter;
import com.juntai.upcodesafe.home_page.inspect.inspect.checkRecord.checkRecordInfo.CheckRecordDetailActivity;
import com.juntai.upcodesafe.utils.HawkProperty;
import com.juntai.upcodesafe.utils.UserInfoManager;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述   行业种类
 * @date 2021/6/1 15:58
 */
public class HomepageChildBusinessFragment extends BaseRecyclerviewFragment<HomePagePresent> implements HomePageContract.IHomePageView {
    @Override
    protected void initView() {
        isLinearLayout = false;
        spanCount = 3;
        super.initView();
    }

    @Override
    protected void lazyLoad() {
        String type = getArguments().getString(BaseInspectContract.TAB_TITLES);
        switch (type) {
            case BaseInspectContract.UNIT_DIRECTOR:
                mPresenter.getHomePageBusinessDirector(getBaseBuilder()
                        .add("departmentId", String.valueOf(UserInfoManager.getDepartmentId())).build(), AppHttpPath.GET_BUSINESS_DIRECTOR);
                break;
            case BaseInspectContract.UNIT_SUPERVISE:
                mPresenter.getHomePageBusinessSupervise(getBaseBuilder()
                        .add("departmentId", String.valueOf(UserInfoManager.getDepartmentId())).build(), AppHttpPath.GET_BUSINESS_DIRECTOR);
                break;
            default:
                break;
        }
    }

    @Override
    protected HomePagePresent createPresenter() {
        return new HomePagePresent();
    }

    public static HomepageChildBusinessFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(BaseInspectContract.TAB_TITLES, type);
        HomepageChildBusinessFragment fragment = new HomepageChildBusinessFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void freshlayoutOnLoadMore() {
    }

    /**
     * 获取数据
     */
    private void getAdapterData() {

    }

    @Override
    protected void freshlayoutOnRefresh() {
        getAdapterData();
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new HomePageSupervisionMenuAdapter(R.layout.homepage_supervision_menu);
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

}
