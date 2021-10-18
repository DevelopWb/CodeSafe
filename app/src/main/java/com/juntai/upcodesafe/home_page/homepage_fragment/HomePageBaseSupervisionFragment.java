package com.juntai.upcodesafe.home_page.homepage_fragment;


import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.BaseAppFragment;
import com.juntai.upcodesafe.home_page.HomePageContract;
import com.juntai.upcodesafe.home_page.HomePagePresent;
import com.juntai.upcodesafe.home_page.inspect.notice.EnterpriseNoticeActivity;
import com.juntai.upcodesafe.home_page.search.SearchActivity;
import com.juntai.upcodesafe.mine.MyCenterContract;

/**
 * @aouther tobato
 * @description 描述  homepage  监管端fragment
 * @date 2021/4/18 14:59
 */
public abstract class HomePageBaseSupervisionFragment extends BaseAppFragment<HomePagePresent> implements HomePageContract.IHomePageView,
        View.OnClickListener {

    private LinearLayout mSearchLl;
    private ImageView mAddEnterpriseIv;
    private HomePageEnterpriseMenuAdapter menuAdapter;
    private ConstraintLayout mNoticeCl;
    /**
     * 当前登录：
     */
    private TextView mCurrentUserTv;
    private FrameLayout mHomepageChildFl;

    @Override
    protected int getLayoutRes() {
        return R.layout.homepage_base_child_fg;
    }

    @Override
    protected void initView() {


        mSearchLl = (LinearLayout) getView(R.id.search_ll);
        mSearchLl.setOnClickListener(this);
        mAddEnterpriseIv = (ImageView) getView(R.id.add_enterprise_iv);
        mAddEnterpriseIv.setOnClickListener(this);
        mNoticeCl = (ConstraintLayout) getView(R.id.notice_cl);
        mNoticeCl.setOnClickListener(this);
        mCurrentUserTv = (TextView) getView(R.id.current_user_tv);
        mHomepageChildFl = (FrameLayout) getView(R.id.homepage_child_fl);
        if (getChildFragmentView() != null) {
            mHomepageChildFl.addView(getChildFragmentView());
        }

    }

    protected abstract View getChildFragmentView();


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
                //  跳转到搜索界面
                startActivity(new Intent(mContext, SearchActivity.class));
                break;
            case R.id.add_enterprise_iv:
                // TODO: 2021-10-18 添加企业 
                break;
            case R.id.notice_cl:
                startActivity(new Intent(mContext, EnterpriseNoticeActivity.class));
                break;
        }
    }

}
