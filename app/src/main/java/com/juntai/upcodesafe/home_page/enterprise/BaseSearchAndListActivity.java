package com.juntai.upcodesafe.home_page.enterprise;


import android.support.v7.widget.SearchView;
import android.view.View;

import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.BaseRecyclerviewActivity;
import com.juntai.upcodesafe.bean.NoticeBean;
import com.juntai.upcodesafe.home_page.HomePageContract;
import com.juntai.upcodesafe.home_page.HomePagePresent;
import com.juntai.upcodesafe.utils.StringTools;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述   搜索和列表的基类
 * @date 2021-10-09 10:21
 */
public abstract class BaseSearchAndListActivity extends BaseRecyclerviewActivity<HomePagePresent> implements HomePageContract.IHomePageView {

    private SearchView mSearchContentSv;
    @Override
    protected HomePagePresent createPresenter() {
        return new HomePagePresent();
    }
    @Override
    protected void freshlayoutOnLoadMore() {

    }

    @Override
    protected void freshlayoutOnRefresh() {
        startSearch(mSearchContentSv.getQuery().toString().trim());
    }


    @Override
    public int getLayoutView() {
        return R.layout.activity_base_search_and_list;
    }

    @Override
    public void initView() {
        super.initView();
        setTitleName(getTitleName());
        mSmartrefreshlayout.setEnableLoadMore(false);
        mSearchContentSv = (SearchView) findViewById(R.id.search_content_sv);
        mSearchContentSv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!StringTools.isStringValueOk(mSearchContentSv.getQuery().toString().trim())) {
                    ToastUtils.warning(mContext, "请输入要搜索的内容");
                    return;
                }
                startSearch(mSearchContentSv.getQuery().toString().trim());
            }
        });
        mSearchContentSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (!StringTools.isStringValueOk(s)) {
                    ToastUtils.warning(mContext, "请输入要搜索的内容");
                    return false;
                }
                // 调用搜索接口
                startSearch(mSearchContentSv.getQuery().toString().trim());

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    protected abstract void startSearch(String s);

    protected abstract String getTitleName();

    @Override
    public void initData() {
        startSearch("");
    }


    @Override
    public void onSuccess(String tag, Object o) {
       super.onSuccess(tag,o);
        switch (tag) {
            case AppHttpPath.HOMEPAGE_NOTICE:
                NoticeBean noticeBean = (NoticeBean) o;
                if (noticeBean != null) {
                    List<NoticeBean.DataBean> arrays = noticeBean.getData();
                    adapter.setNewData(arrays);
                }
                break;
            default:
                break;
        }
    }
}
