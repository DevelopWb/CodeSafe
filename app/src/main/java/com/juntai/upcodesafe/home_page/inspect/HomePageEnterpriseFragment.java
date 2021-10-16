package com.juntai.upcodesafe.home_page.inspect;


import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.base.BaseMvpFragment;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.HomePageMenuBean;
import com.juntai.upcodesafe.home_page.HomePageContract;
import com.juntai.upcodesafe.home_page.HomePageMenuAdapter;
import com.juntai.upcodesafe.home_page.HomePagePresent;
import com.juntai.upcodesafe.home_page.QRScanActivity;
import com.juntai.upcodesafe.home_page.inspect.accidentWarn.AccidentWarnActivity;
import com.juntai.upcodesafe.home_page.inspect.educateOnline.EducateOnlineActivity;
import com.juntai.upcodesafe.home_page.inspect.notice.EnterpriseNoticeActivity;
import com.juntai.upcodesafe.home_page.inspect.selfcheck.SelfCheckActivity;
import com.juntai.upcodesafe.mine.MyCenterContract;
import com.juntai.disabled.basecomponent.utils.AppUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * @aouther tobato
 * @description 描述  homepage
 * @date 2021/4/18 14:59
 */
public class HomePageEnterpriseFragment extends BaseMvpFragment<HomePagePresent> implements MyCenterContract.ICenterView,
        View.OnClickListener {

    private LinearLayout mSearchLl;
    private RecyclerView mRecyclerview;
    private SmartRefreshLayout mSmartrefreshlayout;
    private HomePageMenuAdapter menuAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.homepage_enterprise_fg;
    }

    @Override
    protected void initView() {


        mSearchLl = (LinearLayout) getView(R.id.search_ll);
        mSearchLl.setOnClickListener(this);
        mRecyclerview = (RecyclerView) getView(R.id.recyclerview);
        mSmartrefreshlayout = (SmartRefreshLayout) getView(R.id.smartrefreshlayout);
        mSmartrefreshlayout.setEnableLoadMore(false);
        mSmartrefreshlayout.setEnableRefresh(false);
        menuAdapter = new HomePageMenuAdapter(R.layout.homepage_enterprise_menu_item);
        GridLayoutManager manager = new GridLayoutManager(mContext, 2);
        mRecyclerview.setLayoutManager(manager);
        mRecyclerview.setAdapter(menuAdapter);
        menuAdapter.setNewData(mPresenter.getMenuList());
        menuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomePageMenuBean menuBean = (HomePageMenuBean) adapter.getData().get(position);
                String menuName = menuBean.getMenuName();
                if (TextUtils.isEmpty(menuName)) {
                    return;
                }
                Intent intent = new Intent();
                switch (menuName) {
                    case HomePageContract.HOMEPAGE_MENU_NOTICE:
                        intent.setClass(mContext, EnterpriseNoticeActivity.class);
                        break;
                    case HomePageContract.HOMEPAGE_MENU_ENTERPRISE_CHECK:
                        intent.setClass(mContext, SelfCheckActivity.class);
                        break;
                    case HomePageContract.HOMEPAGE_MENU_WRING:
                        // 跳转到事故警示
                        intent.setClass(mContext, AccidentWarnActivity.class);
                        break;
                    case HomePageContract.HOMEPAGE_MENU_EDUCATION:
                        //   跳转到在线教育
                        intent.setClass(mContext, EducateOnlineActivity.class);
                        break;
                    default:
                        break;
                }
                startActivity(intent);
            }
        });

        mSearchLl = (LinearLayout) getView(R.id.search_ll);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    protected void lazyLoad() {
    }

    @Override
    protected HomePagePresent createPresenter() {
        return new HomePagePresent();
    }


    @Override
    public void onSuccess(String tag, Object o) {
    }

    @Override
    public void onError(String tag, Object o) {
        ToastUtils.error(mContext, String.valueOf(o));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.search_ll:
                break;
            case R.id.scan_iv:
                getActivity().startActivityForResult(new Intent(getActivity(),
                        QRScanActivity.class), AppUtils.QR_SCAN_NOMAL);
                break;
        }
    }
}
