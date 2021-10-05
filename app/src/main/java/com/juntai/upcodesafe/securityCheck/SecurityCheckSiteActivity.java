package com.juntai.upcodesafe.securityCheck;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.BaseAppActivity;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectContract;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectPresent;
import com.juntai.upcodesafe.utils.StringTools;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * @aouther tobato
 * @description 描述  治安巡检
 * @date 2021/4/18 16:43
 */
public class SecurityCheckSiteActivity extends BaseAppActivity<BaseInspectPresent> implements BaseInspectContract.IInspectView {

    private SearchView mSearchContentSv;
    private RecyclerView mRecyclerview;
    private SmartRefreshLayout mSmartrefreshlayout;
    private SecurityCheckSiteAdapter adapter;

    @Override
    protected BaseInspectPresent createPresenter() {
        return null;
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_security_check_site;
    }

    @Override
    public void initView() {
        setTitleName("治安巡检点");
        mSearchContentSv = (SearchView) findViewById(R.id.search_content_sv);
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new SecurityCheckSiteAdapter(R.layout.check_item);
        initRecyclerview(mRecyclerview, adapter, LinearLayoutManager.VERTICAL);
        adapter.setNewData(getTestData());
        mSmartrefreshlayout = (SmartRefreshLayout) findViewById(R.id.smartrefreshlayout);
        mSearchContentSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (!StringTools.isStringValueOk(s)) {
                    ToastUtils.warning(mContext, "请输入要搜索的内容");
                    return false;
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    @Override
    public void initData() {

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext,SecurityInspectionInfoActivity.class));
            }
        });

    }


    @Override
    public void onSuccess(String tag, Object o) {

    }

}
