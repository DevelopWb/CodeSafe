package com.juntai.upcodesafe.home_page.industryDetail;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.baidu.mapapi.model.LatLng;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.BaseRecyclerviewFragment;
import com.juntai.upcodesafe.bean.SearchBean;
import com.juntai.upcodesafe.bean.UnitOfInductryBean;
import com.juntai.upcodesafe.home_page.HomePageContract;
import com.juntai.upcodesafe.home_page.HomePagePresent;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectContract;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectionInfoActivity;
import com.juntai.upcodesafe.home_page.inspect.inspect.UnitInfoActivity;
import com.juntai.upcodesafe.utils.HawkProperty;
import com.juntai.upcodesafe.utils.UserInfoManager;
import com.orhanobut.hawk.Hawk;

import java.util.List;

import okhttp3.FormBody;

/**
 * @aouther tobato
 * @description 描述   行业下面的企业单位
 * @date 2021/6/1 15:58
 */
public class UnitListOfIndustryFragment extends BaseRecyclerviewFragment<HomePagePresent> implements HomePageContract.IHomePageView {

    private String fragmentTag;


    @Override
    protected void lazyLoad() {
        fragmentTag = getArguments().getString(BaseInspectContract.TAB_TITLES);
        if (BaseInspectContract.TAB_WAIT_FOR_ACCEPT.equals(fragmentTag)) {
            Hawk.put(HawkProperty.IS_YANSHOU_CHECK,true);
        }else {
            Hawk.put(HawkProperty.IS_YANSHOU_CHECK,false);
        }

        mSmartrefreshlayout.setEnableLoadMore(false);
        getAdapterData();
        getPresentActivity().setTypeTypeSelectedCallBack(new IndustryInfoActivity.OnRefreshDataCallBack() {
            @Override
            public void refreshData() {
                getAdapterData();
            }
        });
    }

    @Override
    protected HomePagePresent createPresenter() {
        return new HomePagePresent();
    }

    public static UnitListOfIndustryFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(BaseInspectContract.TAB_TITLES, type);
        UnitListOfIndustryFragment fragment = new UnitListOfIndustryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        adapter.setEmptyView(getBaseActivity().getAdapterEmptyView("一条数据也没有~_~", -1));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                UnitOfInductryBean.DataBean dataBean = (UnitOfInductryBean.DataBean) adapter.getData().get(position);
                startActivity(new Intent(mContext, UnitInfoActivity.class)
                        .putExtra(BaseInspectionInfoActivity.BASE_STRING, BaseInspectionInfoActivity.BASE_STRING_VALUE1)
                        .putExtra(BaseInspectionInfoActivity.BASE_ID, String.valueOf(dataBean.getId())));
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                UnitOfInductryBean.DataBean  databean = (UnitOfInductryBean.DataBean) adapter.getData().get(position);
                switch (view.getId()) {
                    case R.id.item_navigation_tv:
                        if (TextUtils.isEmpty(databean.getLatitude()) || TextUtils.isEmpty(databean.getLongitude())) {
                            ToastUtils.toast(mContext, "无法获取经纬度，不能导航");
                            return;
                        }
                        getBaseAppActivity().navigationLogic(new LatLng(Double.parseDouble(databean.getLatitude()),
                                Double.parseDouble(databean.getLongitude())), databean.getAddress());
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void freshlayoutOnLoadMore() {
        getAdapterData();
    }

    private IndustryInfoActivity getPresentActivity() {
        return (IndustryInfoActivity) getActivity();
    }

    /**
     * 获取数据
     */
    private void getAdapterData() {
        FormBody.Builder builder = getBaseBuilder()
                .add("unitTypeId", String.valueOf(getPresentActivity().baseId))
                .add("keyword", getPresentActivity().mSearchSv.getQuery().toString().trim());
        if (0 != getPresentActivity().currentTypeId) {
            builder.add("searchTerritoryId", String.valueOf(getPresentActivity().currentTypeId));
        }
        if (0 != getPresentActivity().mViewpageVp.getCurrentItem()) {
            builder.add("typeId", String.valueOf(getPresentActivity().mViewpageVp.getCurrentItem()));
        }
        if (UserInfoManager.getAccountTypeId() == 1) {
            builder.add("hasDirector", getPresentActivity().typeid);
        }

        switch (UserInfoManager.getAccountTypeId()) {
            case 1:
                builder.add("departmentId", String.valueOf(UserInfoManager.getDepartmentId()));

                break;
            case 2:
                builder.add("territoryId", String.valueOf(UserInfoManager.getTerritoryId()));
                break;
            case 3:
                builder.add("gridId", String.valueOf(UserInfoManager.getGridId()));
                break;
            default:
                break;
        }
        if (getPresentActivity().isNormalCheck) {
            mPresenter.getNormalCheckList(builder.build(), AppHttpPath.GET_NORMAL_CHECK_LIST);
        } else {
            mPresenter.getSpecialCheckList(builder.build(), AppHttpPath.GET_NORMAL_CHECK_LIST);

        }
    }

    @Override
    protected void freshlayoutOnRefresh() {
        getAdapterData();
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new UnitOfIndustryAdapter(R.layout.check_unit_item);
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        UnitOfInductryBean unitOfInductryBean = (UnitOfInductryBean) o;
        if (unitOfInductryBean != null) {
            List<UnitOfInductryBean.DataBean> arrays = unitOfInductryBean.getData();
            adapter.setNewData(arrays);
        }
    }

}
