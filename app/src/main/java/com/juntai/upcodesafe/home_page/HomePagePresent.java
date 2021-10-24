package com.juntai.upcodesafe.home_page;

import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.upcodesafe.AppNetModule;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.HomeBusinessBean;
import com.juntai.upcodesafe.bean.HomePageMenuBean;
import com.juntai.upcodesafe.bean.LableBean;
import com.juntai.upcodesafe.bean.NoticeBean;
import com.juntai.upcodesafe.bean.SearchBean;
import com.juntai.upcodesafe.bean.SearchResultBean;
import com.juntai.upcodesafe.bean.UnitOfInductryBean;
import com.juntai.upcodesafe.bean.UnitsBean;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;

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

        arrays.add(new HomePageMenuBean(HomePageContract.HOMEPAGE_MENU_NOTICE, "Message\nNotification",R.mipmap.news_tag));
        arrays.add(new HomePageMenuBean(HomePageContract.HOMEPAGE_MENU_ENTERPRISE_CHECK, "Enterprise\nComprehensive", R.mipmap.enterprise_tag));
        arrays.add(new HomePageMenuBean(HomePageContract.HOMEPAGE_MENU_WRING, "The accident\nWarning", R.mipmap.warn_tag));
        arrays.add(new HomePageMenuBean(HomePageContract.HOMEPAGE_MENU_EDUCATION, "Online\nEducation", R.mipmap.online_tag));

        return arrays;
    }
    public void searchAccountNature(RequestBody requestBody, String tag) {
        AppNetModule
                .createrRetrofit()
                .searchAccountNature(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<UnitsBean>(getView()) {
                    @Override
                    public void onSuccess(UnitsBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }

                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }
    /**
     * 安全生产主管列表
     * @param requestBody
     * @param tag
     */
    public void getHomePageBusinessDirector(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getHomePageBusinessDirector(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<HomeBusinessBean>(getView()) {
                    @Override
                    public void onSuccess(HomeBusinessBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }
    /**
     * 安全生产主管列表
     * @param requestBody
     * @param tag
     */
    public void getHomePageBusinessSupervise(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getHomePageBusinessSupervise(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<HomeBusinessBean>(getView()) {
                    @Override
                    public void onSuccess(HomeBusinessBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }
    /**
     * 属地
     * @param requestBody
     * @param tag
     */
    public void getHomePageBusinessTerritory(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getHomePageBusinessTerritory(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<HomeBusinessBean>(getView()) {
                    @Override
                    public void onSuccess(HomeBusinessBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }
    /**
     * 网格
     * @param requestBody
     * @param tag
     */
    public void getHomePageBusinessGrid(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getHomePageBusinessGrid(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<HomeBusinessBean>(getView()) {
                    @Override
                    public void onSuccess(HomeBusinessBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }
    /**
     * 通知公告
     * @param requestBody
     * @param tag
     */
    public void getHomePageNotice(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getHomePageNotice(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<NoticeBean>(getView()) {
                    @Override
                    public void onSuccess(NoticeBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }
    public void getHomePageAccident(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getHomePageAccident(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<NoticeBean>(getView()) {
                    @Override
                    public void onSuccess(NoticeBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }
    public void getEducationTag(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getEducationTag(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<LableBean>(getView()) {
                    @Override
                    public void onSuccess(LableBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o.getData());
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }
    public void getEducationList(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getEducationList(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<NoticeBean>(getView()) {
                    @Override
                    public void onSuccess(NoticeBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o.getData());
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }
    public void search(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .search(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<SearchBean>(getView()) {
                    @Override
                    public void onSuccess(SearchBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }
    /**
     * @param requestBody
     * @param tag
     */
    public void searchMore(RequestBody requestBody, String tag) {

        AppNetModule.createrRetrofit()
                .searchMore(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<SearchResultBean>(getView()) {
                    @Override
                    public void onSuccess(SearchResultBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });

    }
    /**
     * @param requestBody
     * @param tag
     */
    public void getNormalCheckList(RequestBody requestBody, String tag) {

        AppNetModule.createrRetrofit()
                .getNormalCheckList(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<UnitOfInductryBean>(getView()) {
                    @Override
                    public void onSuccess(UnitOfInductryBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });

    }
    /**
     * @param requestBody
     * @param tag
     */
    public void getSpecialCheckList(RequestBody requestBody, String tag) {

        AppNetModule.createrRetrofit()
                .getSpecialCheckList(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<UnitOfInductryBean>(getView()) {
                    @Override
                    public void onSuccess(UnitOfInductryBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });

    }
}
