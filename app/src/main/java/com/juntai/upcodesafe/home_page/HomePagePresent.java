package com.juntai.upcodesafe.home_page;

import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.HomePageMenuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther Ma
 * @date 2019/3/14
 */
public class HomePagePresent extends BasePresenter<IModel, HomePageContract.IHomePageView> implements HomePageContract.IHomePagePresent {
    @Override
    protected IModel createModel() {
        return null;
    }



    public List<HomePageMenuBean> getMenuList(){

        List<HomePageMenuBean> arrays = new ArrayList<>();

        arrays.add(new HomePageMenuBean(HomePageContract.HOMEPAGE_MENU_NOTICE, "Message\nNotification",R.drawable.sp_filled_gray_circle));
        arrays.add(new HomePageMenuBean(HomePageContract.HOMEPAGE_MENU_ENTERPRISE_CHECK, "Enterprise\nComprehensive", R.drawable.sp_filled_gray_circle));
        arrays.add(new HomePageMenuBean(HomePageContract.HOMEPAGE_MENU_WRING, "The accident\nWarning", R.drawable.sp_filled_gray_circle));
        arrays.add(new HomePageMenuBean(HomePageContract.HOMEPAGE_MENU_EDUCATION, "Online\nEducation", R.drawable.sp_filled_gray_circle));

        return arrays;
    }
}
