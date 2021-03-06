package com.juntai.upcodesafe.home_page.homepage_fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.BaseAppFragment;
import com.juntai.upcodesafe.home_page.HomePageContract;
import com.juntai.upcodesafe.home_page.HomePagePresent;
import com.juntai.upcodesafe.utils.UserInfoManager;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-10-18 9:42
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-18 9:42
 */
public class HomePageFragment extends BaseAppFragment<HomePagePresent> implements HomePageContract.IHomePageView {
    private FragmentTransaction transaction;
    private HomePageEnterpriseFragment homePageEnterpriseFragment;
    private HomepageSupervisionFragment homePageSupervisionFragment;
    private HomepageOtherSupervisionFragment mHomepageOtherSupervisionFragment;
    private FragmentManager fManager;

    //    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        // TODO Auto-generated method stub
//        fManager = getFragmentManager();
//        if (savedInstanceState != null) {
//            mHomepageOtherSupervisionFragment= (HomepageOtherSupervisionFragment) fManager.findFragmentByTag("mHomepageOtherSupervisionFragment");
//            homePageEnterpriseFragment= (HomePageEnterpriseFragment) fManager.findFragmentByTag("homePageEnterpriseFragment");
//            homePageSupervisionFragment= (HomepageSupervisionFragment) fManager.findFragmentByTag("homePageSupervisionFragment");
//        }
//        super.onCreate(savedInstanceState);
//    }
    @Override
    protected HomePagePresent createPresenter() {
        return null;
    }

    @Override
    protected void lazyLoad() {
        int accountType = UserInfoManager.getAccountTypeId();
        switch (accountType) {
            case 1:
                showFragment(homePageSupervisionFragment);
                break;
            case 2:
                showFragment(mHomepageOtherSupervisionFragment);
                break;
            case 3:
                showFragment(mHomepageOtherSupervisionFragment);
                break;
            case 4:
                showFragment(homePageEnterpriseFragment);
                break;
            default:
                break;
        }

    }

    /**
     * 展示fragment
     *
     * @param fragment
     */
    private void showFragment(Fragment fragment) {
        if (fragment.isAdded()) {
            transaction.show(fragment);
        } else {
            transaction.add(R.id.homepage_fl, fragment, fragment.getClass().getName());
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.homepage_fg;
    }

    @Override
    protected void initView() {
        homePageEnterpriseFragment = new HomePageEnterpriseFragment();
        homePageSupervisionFragment = new HomepageSupervisionFragment();
        mHomepageOtherSupervisionFragment = new HomepageOtherSupervisionFragment();
        fManager = getFragmentManager();
        transaction = fManager.beginTransaction();
//                .add(R.id.homepage_fl, mHomepageOtherSupervisionFragment,"mHomepageOtherSupervisionFragment")
//                .add(R.id.homepage_fl, homePageEnterpriseFragment,"homePageEnterpriseFragment")
//                .add(R.id.homepage_fl, homePageSupervisionFragment,"homePageSupervisionFragment");
        transaction.commit();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(String tag, Object o) {

    }

}
