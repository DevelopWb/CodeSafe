package com.juntai.upcodesafe.home_page.enterprise;


import android.support.v7.widget.SearchView;

import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.BaseRecyclerviewActivity;
import com.juntai.upcodesafe.home_page.HomePageContract;
import com.juntai.upcodesafe.home_page.HomePagePresent;
import com.juntai.upcodesafe.utils.StringTools;

/**
 * @aouther tobato
 * @description 描述   搜索和列表的基类
 * @date 2021-10-09 10:21
 */
public abstract class BaseSearchAndListActivity<P extends BasePresenter> extends BaseRecyclerviewActivity<P>  {

    private SearchView mSearchContentSv;

    @Override
    public int getLayoutView() {
        return R.layout.activity_base_search_and_list;
    }

    @Override
    public void initView() {
        super.initView();
        setTitleName(getTitleName());
        adapter.setNewData(getTestData());
        mSearchContentSv = (SearchView) findViewById(R.id.search_content_sv);
        mSearchContentSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (!StringTools.isStringValueOk(s)) {
                    ToastUtils.warning(mContext, "请输入要搜索的内容");
                    return false;
                }
                // TODO: 2021-10-09 调用搜索接口
//                startSearch(s);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    protected abstract String getTitleName();

    @Override
    public void initData() {

    }



    @Override
    public void onSuccess(String tag, Object o) {
    }
}
