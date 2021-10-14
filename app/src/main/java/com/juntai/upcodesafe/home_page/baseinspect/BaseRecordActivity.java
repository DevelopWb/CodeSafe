package com.juntai.upcodesafe.home_page.baseinspect;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.BaseAppActivity;
import com.juntai.upcodesafe.bean.RectifyNoticeListBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  历史记录
 * @CreateDate: 2021/5/15 15:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/15 15:48
 */
public abstract class BaseRecordActivity extends BaseAppActivity<BaseInspectPresent> implements BaseInspectContract.IInspectView {

    private RecyclerView mRecyclerview;
    private SmartRefreshLayout mSmartrefreshlayout;
    public BaseQuickAdapter adapter;
    public int currentPage = 1, pagesize = 10;

    public static String ID = "id";
    public int id;

    @Override
    public int getLayoutView() {
        return R.layout.recycleview_layout;
    }

    @Override
    public void initView() {
        setTitleName(getTitleName());
        if (getIntent() != null) {
            id = getIntent().getIntExtra(ID, 0);
        }
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        mSmartrefreshlayout = (SmartRefreshLayout) findViewById(R.id.smartrefreshlayout);
        adapter = getAdapter();
        adapter.setEmptyView(getAdapterEmptyView("一条记录也没有...",-1));
        initRecyclerview(mRecyclerview, adapter, LinearLayoutManager.VERTICAL);
        mSmartrefreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mSmartrefreshlayout.setNoMoreData(false);
                currentPage = 1;
                requestHisData();
            }
        });
        mSmartrefreshlayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                currentPage++;
                requestHisData();
            }
        });
    }

    protected abstract String getTitleName();

    /**
     * 请求历史记录
     */
    protected abstract void requestHisData();

    protected abstract BaseQuickAdapter getAdapter();

    @Override
    public void initData() {
        requestHisData();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                onAdapterItemClick(adapter, position);
            }
        });

    }

    protected abstract void onAdapterItemClick(BaseQuickAdapter adapter, int position);

    @Override
    protected BaseInspectPresent createPresenter() {
        return new BaseInspectPresent();
    }

    @Override
    public void onSuccess(String tag, Object o) {
        mSmartrefreshlayout.finishRefresh();
        mSmartrefreshlayout.finishLoadMore();
        switch (tag) {
            case AppHttpPath.GET_RECTIFY_NOTICE_LIST:
                RectifyNoticeListBean rectifyNoticeListBean = (RectifyNoticeListBean) o;
                if (rectifyNoticeListBean != null) {
                    List<RectifyNoticeListBean.DataBean> arrays =
                            rectifyNoticeListBean.getData();
                    if (currentPage == 1) {
                        adapter.setNewData(arrays);
                    } else {
                        adapter.addData(arrays);
                    }
                    if (arrays != null && arrays.size() < pagesize) {
                        mSmartrefreshlayout.finishLoadMoreWithNoMoreData();
                    }
                }
                break;
            default:
                break;
        }
    }

}
