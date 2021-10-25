package com.juntai.upcodesafe.home_page.educateOnline;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.util.SparseArray;
import android.view.View;

import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.BaseAppActivity;
import com.juntai.upcodesafe.base.customview.CustomViewPager;
import com.juntai.upcodesafe.base.customview.MainPagerAdapter;
import com.juntai.upcodesafe.bean.LableBean;
import com.juntai.upcodesafe.home_page.HomePageContract;
import com.juntai.upcodesafe.home_page.HomePagePresent;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述  在线教育
 * @date 2021-10-09 13:49
 */
public class EducateOnlineActivity extends BaseAppActivity<HomePagePresent> implements HomePageContract.IHomePageView {

    private SearchView mSearchContentSv;
    private TabLayout mEducationTb;
    private CustomViewPager mEducationVp;

    OnSearchCallBack onSearchCallBack;


    public void setOnSearchCallBack(OnSearchCallBack onSearchCallBack) {
        this.onSearchCallBack = onSearchCallBack;
    }

    @Override
    protected HomePagePresent createPresenter() {
        return new HomePagePresent();
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
        mSearchContentSv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始搜索
                if (onSearchCallBack != null) {
                    onSearchCallBack.startSearch();
                }
            }
        });
        mSearchContentSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //开始搜索
                if (onSearchCallBack != null) {
                    onSearchCallBack.startSearch();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }


    private void initTab(String[] titles, SparseArray<Fragment> fragments) {
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), getApplicationContext(),
                titles, fragments);
        mEducationVp.setAdapter(adapter);
        mEducationVp.setOffscreenPageLimit(titles.length);
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
        mPresenter.getEducationTag(getBaseBuilder().build(), "");
    }

    /**
     * 获取搜索内容
     * @return
     */
    public String getSearchContent() {
        return mSearchContentSv.getQuery().toString().trim();
    }


    @Override
    public void onSuccess(String tag, Object o) {
        if (o != null) {
            List<LableBean.DataBean> arrays = (List<LableBean.DataBean>) o;
            if (arrays!=null&&!arrays.isEmpty()) {
                String[] tabTitles = new String[arrays.size()];
                SparseArray<Fragment> fragments = new SparseArray<>();
                for (int i = 0; i < arrays.size(); i++) {
                    LableBean.DataBean bean = arrays.get(i);
                    tabTitles[i] = bean.getLabelName();
                    fragments.append(i, EducationOnlineFragment.getInstance(bean.getId()));
                }
                initTab(tabTitles, fragments);
            }
        }
    }


    public interface  OnSearchCallBack {
        void startSearch();
    }
}
