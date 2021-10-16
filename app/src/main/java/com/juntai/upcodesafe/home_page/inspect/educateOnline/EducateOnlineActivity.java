package com.juntai.upcodesafe.home_page.inspect.educateOnline;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.util.SparseArray;

import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.BaseAppActivity;
import com.juntai.upcodesafe.base.customview.CustomViewPager;
import com.juntai.upcodesafe.base.customview.MainPagerAdapter;

/**
 * @aouther tobato
 * @description 描述  在线教育
 * @date 2021-10-09 13:49
 */
public class EducateOnlineActivity extends BaseAppActivity {

    private SearchView mSearchContentSv;
    private TabLayout mEducationTb;
    private CustomViewPager mEducationVp;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_educate_online;
    }

    @Override
    public void initView() {
        setTitleName("在线教育");
        mSearchContentSv = (SearchView) findViewById(R.id.search_content_sv);
        mEducationTb = (TabLayout) findViewById(R.id.education_tb);
        mEducationVp = (CustomViewPager) findViewById(R.id.education_vp);
        initTab();
        mSearchContentSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //刷新列表
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    protected SparseArray<Fragment> getFragments() {
        SparseArray<Fragment> arrays = new SparseArray<>();
        arrays.append(0, EducationOnlineFragment.getInstance(EducationOnlineFragment.EDUCATE_TAB_PROFESSIONAL));
        arrays.append(1, EducationOnlineFragment.getInstance(EducationOnlineFragment.EDUCATE_TAB_SELFL));
        arrays.append(2, EducationOnlineFragment.getInstance(EducationOnlineFragment.EDUCATE_TAB_IMPORTANT_WORK));
        arrays.append(3, EducationOnlineFragment.getInstance(EducationOnlineFragment.EDUCATE_TAB_SAFE_TRAIN));
        return arrays;
    }

    protected String[] getTabTitles() {
        return new String[]{EducationOnlineFragment.EDUCATE_TAB_PROFESSIONAL,
                EducationOnlineFragment.EDUCATE_TAB_SELFL, EducationOnlineFragment.EDUCATE_TAB_IMPORTANT_WORK
                , EducationOnlineFragment.EDUCATE_TAB_SAFE_TRAIN};
    }

    private void initTab() {
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), getApplicationContext(),
                getTabTitles(),
                getFragments());
        mEducationVp.setAdapter(adapter);
        mEducationVp.setOffscreenPageLimit(getTabTitles().length);
        /*viewpager切换监听，包含滑动点击两种*/
        mEducationVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        mEducationTb.setupWithViewPager(mEducationVp);
        /**
         * 添加自定义tab布局
         * */
        for (int i = 0; i < mEducationTb.getTabCount(); i++) {
            TabLayout.Tab tab = mEducationTb.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(adapter.getTabView(i));
            }
        }
        /*viewpager切换默认第一个*/
        mEducationVp.setCurrentItem(0);
    }

    @Override
    public void initData() {

    }


    @Override
    public void onSuccess(String tag, Object o) {

    }

}
