package com.juntai.upcodesafe.home_page.industryDetail;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.utils.PickerManager;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.BaseAppActivity;
import com.juntai.upcodesafe.base.customview.CustomViewPager;
import com.juntai.upcodesafe.base.customview.MainPagerAdapter;
import com.juntai.upcodesafe.bean.UnitsBean;
import com.juntai.upcodesafe.home_page.HomePageContract;
import com.juntai.upcodesafe.home_page.HomePagePresent;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectContract;
import com.juntai.upcodesafe.utils.HawkProperty;
import com.juntai.upcodesafe.utils.UserInfoManager;
import com.orhanobut.hawk.Hawk;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  有条件搜索的基类  行业详情
 * @CreateDate: 2021-10-21 9:38
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-21 9:38
 */
public class IndustryInfoActivity extends BaseAppActivity<HomePagePresent> implements HomePageContract.IHomePageView, View.OnClickListener {
    public SearchView mSearchSv;
    /**
     * 盗窃
     */
    private TextView mSearchTypeTv;
    private LinearLayout mSearchTypeLl;
    public int currentTypeId = 0;

    private OnRefreshDataCallBack refreshData;
    private List<UnitsBean.DataBean> arrays;
    public static String BASE_ID = "baseId";
    public static String TYPE_ID = "type";
    public int baseId;
    public String  typeid;
    public boolean  isNormalCheck = true;//默认是常规检查
    /**
     * 常规检查
     */
    private TextView mNormalCheckTv;
    /**
     * 专项检查
     */
    private TextView mSpecialCheckTv;
    private ConstraintLayout mCheckTypeCl;
    private TabLayout mTabTb;
    public CustomViewPager mViewpageVp;

    public void setTypeTypeSelectedCallBack(OnRefreshDataCallBack typeSelectedCallBack) {
        this.refreshData = typeSelectedCallBack;
    }

    @Override
    protected HomePagePresent createPresenter() {
        return new HomePagePresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.industry_info_layout;
    }

    @Override
    public void initView() {
        Hawk.put(HawkProperty.IS_NORMAL_CHECK,true);
        if (getIntent() != null) {
            baseId = getIntent().getIntExtra(BASE_ID, 0);
            typeid = getIntent().getStringExtra(TYPE_ID);
        }
        setTitleName("行业明细");
        mSearchSv = (SearchView) findViewById(R.id.search_sv);
        mSearchSv.setOnClickListener(this);
        mSearchTypeTv = (TextView) findViewById(R.id.search_type_tv);
        mSearchTypeLl = (LinearLayout) findViewById(R.id.search_type_ll);
        mSearchTypeLl.setOnClickListener(this);
        mSearchSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //刷新列表
                if (refreshData != null) {
                    refreshData.refreshData();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        mNormalCheckTv = (TextView) findViewById(R.id.normal_check_tv);
        mSpecialCheckTv = (TextView) findViewById(R.id.special_check_tv);
        mNormalCheckTv.setOnClickListener(this);
        mSpecialCheckTv.setOnClickListener(this);
        mCheckTypeCl = (ConstraintLayout) findViewById(R.id.check_type_cl);
        if (3== UserInfoManager.getAccountTypeId()) {
            //网格员没有专属检查
            mCheckTypeCl.setVisibility(View.GONE);
        }else {
            mCheckTypeCl.setVisibility(View.VISIBLE);

        }
        mTabTb = (TabLayout) findViewById(R.id.tab_tb);
        mViewpageVp = (CustomViewPager) findViewById(R.id.viewpage_vp);
        initTab();
    }


    @Override
    public void initData() {
        //获取搜索条件
        mPresenter.searchAccountNature(getBaseBuilder().add("keyword", "").add("typeId", "2").build(), AppHttpPath.SEARCH_ACCOUNT_NATURE);

    }

    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case AppHttpPath.SEARCH_ACCOUNT_NATURE:
                UnitsBean unitsBean = (UnitsBean) o;
                if (unitsBean != null) {
                    arrays = unitsBean.getData();
                    arrays.add(0, new UnitsBean.DataBean(0, "全部"));
                    mSearchTypeTv.setText(arrays.get(0).getName());
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.normal_check_tv:
                isNormalCheck =true;
                Hawk.put(HawkProperty.IS_NORMAL_CHECK,true);
                mNormalCheckTv.setTextColor(ContextCompat.getColor(mContext,R.color.colorAccent));
                mSpecialCheckTv.setTextColor(ContextCompat.getColor(mContext,R.color.black));
                //刷新列表
                if (refreshData != null) {
                    refreshData.refreshData();
                }
                break;
            case R.id.special_check_tv:
                isNormalCheck = false;
                Hawk.put(HawkProperty.IS_NORMAL_CHECK,false);
                mSpecialCheckTv.setTextColor(ContextCompat.getColor(mContext,R.color.colorAccent));
                mNormalCheckTv.setTextColor(ContextCompat.getColor(mContext,R.color.black));
                //刷新列表
                if (refreshData != null) {
                    refreshData.refreshData();
                }
                break;
            case R.id.search_sv:
                if (refreshData != null) {
                    refreshData.refreshData();
                }
                break;
            case R.id.search_type_ll:
                PickerManager.getInstance().showOptionPicker(mContext, arrays,
                        new PickerManager.OnOptionPickerSelectedListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                UnitsBean.DataBean dataBean = arrays.get(options1);
                                mSearchTypeTv.setText(dataBean.getName());
                                currentTypeId = dataBean.getId();
                                //刷新列表
                                if (refreshData != null) {
                                    refreshData.refreshData();
                                }
                            }
                        });
                break;
        }
    }

    private void initTab() {
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), getApplicationContext(),
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

    /**
     * 类型被选中后的回调
     */
    public interface OnRefreshDataCallBack {

        void refreshData();

    }

    protected SparseArray<Fragment> getFragments() {
        SparseArray<Fragment> arrays = new SparseArray<>();

        arrays.append(0, UnitListOfIndustryFragment.newInstance(BaseInspectContract.TAB_ALL));
        arrays.append(1, UnitListOfIndustryFragment.newInstance(BaseInspectContract.TAB_WAIT_FOR_CHECK));
        arrays.append(2, UnitListOfIndustryFragment.newInstance(BaseInspectContract.TAB_CHECKED));
        arrays.append(3, UnitListOfIndustryFragment.newInstance(BaseInspectContract.TAB_RECTIFYING));
        arrays.append(4, UnitListOfIndustryFragment.newInstance(BaseInspectContract.TAB_WAIT_FOR_ACCEPT));

        return arrays;
    }

    protected String[] getTabTitles() {
        return new String[]{BaseInspectContract.TAB_ALL,
                BaseInspectContract.TAB_WAIT_FOR_CHECK,
                BaseInspectContract.TAB_CHECKED, BaseInspectContract.TAB_RECTIFYING, BaseInspectContract.TAB_WAIT_FOR_ACCEPT};
    }
}
