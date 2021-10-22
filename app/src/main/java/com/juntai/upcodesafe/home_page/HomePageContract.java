package com.juntai.upcodesafe.home_page;

import com.juntai.disabled.basecomponent.mvp.IPresenter;
import com.juntai.disabled.basecomponent.mvp.IView;

/**
 * @aouther tobato
 * @description 描述
 * @date 2020/3/12 16:00
 */
public interface HomePageContract {


     String HOMEPAGE_MENU_NOTICE = "通知公告";
     String HOMEPAGE_MENU_ENTERPRISE_CHECK = "企业自查";
     String HOMEPAGE_UNIT_INFO = "企业自查";
     String HOMEPAGE_MENU_WRING = "事故警示";
     String HOMEPAGE_MENU_EDUCATION = "在线教育";


    interface IHomePageView extends IView {

    }

    interface IHomePagePresent extends IPresenter<IHomePageView> {
    }

}
