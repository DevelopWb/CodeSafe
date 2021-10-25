package com.juntai.upcodesafe.home_page.homepage_fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.customview.CustomViewPager;
import com.juntai.upcodesafe.base.customview.MainPagerAdapter;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectContract;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-10-18 11:21
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-18 11:21
 */
public class HomepageSupervisionFragment extends HomePageBaseSupervisionFragment {

    private TabLayout mTabTb;
    private CustomViewPager mViewpageVp;
    HomepageChildBusinessFragment  childBusinessFragment1 = HomepageChildBusinessFragment.newInstance(BaseInspectContract.UNIT_DIRECTOR);
    HomepageChildBusinessFragment  childBusinessFragment2 = HomepageChildBusinessFragment.newInstance(BaseInspectContract.UNIT_SUPERVISE);

    @Override
    protected View getChildFragmentView() {
        View view  = LayoutInflater.from(mContext).inflate(R.layout.base_tab_page_layout,null);
        mTabTb = (TabLayout) view.findViewById(R.id.tab_tb);
        mTabTb.setTabTextColors(ContextCompat.getColor(mContext,R.color.background2),ContextCompat.getColor(mContext,R.color.black));
        mTabTb.setTabMode(TabLayout.MODE_FIXED);
        mViewpageVp = (CustomViewPager) view.findViewById(R.id.viewpage_vp);
        return view;
    }

    @Override
    protected void refreshData() {
//        childBusinessFragment1.

    }

    private void initTab() {
        MainPagerAdapter adapter = new MainPagerAdapter(getChildFragmentManager(), mContext,
                getTabTitles(),
                getFragments());
        mViewpageVp.setAdapter(adapter);
        mViewpageVp.setOffscreenPageLimit(getTabTitles().length);
        /*viewpager切换监听，包含滑动点击两种*/
        mViewpageVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mTabTb.setupWithViewPager(mViewpageVp);
        /**
         * 添加自定义tab布局
         * */
        for (int i = 0; i < mTabTb.getTabCount(); i++) {
            TabLayout.Tab tab = mTabTb.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(adapter.getTabView(i));
            }
        }
        /*viewpager切换默认第一个*/
        mViewpageVp.setCurrentItem(0);
    }

    protected SparseArray<Fragment> getFragments() {
        SparseArray<Fragment> arrays = new SparseArray<>();

        arrays.append(0,childBusinessFragment1 );
        arrays.append(1, childBusinessFragment2);

        return arrays;
    }

    protected String[] getTabTitles() {
        return new String[]{BaseInspectContract.UNIT_DIRECTOR,
                BaseInspectContract.UNIT_SUPERVISE};
    }

    @Override
    protected void initData() {
        initTab();
    }
}
