package com.juntai.upcodesafe.home_page.inspect.educateOnline;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.mvp.IPresenter;
import com.juntai.disabled.basecomponent.utils.EventManager;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.BaseRecyclerviewFragment;
import com.juntai.upcodesafe.bean.EducationListBean;
import com.juntai.upcodesafe.home_page.HomePageContract;
import com.juntai.upcodesafe.home_page.HomePagePresent;
import com.juntai.upcodesafe.home_page.inspect.notice.EnterpriseNoticeAdapter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import okhttp3.FormBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-10-09 14:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-09 14:55
 */
public class EducationOnlineFragment extends BaseRecyclerviewFragment<HomePagePresent> implements HomePageContract.IHomePageView {
    public static String EDUCATE_TYPE = "educatetype";

    private int lableId;

    public static EducationOnlineFragment getInstance(int lableId) {
        Bundle bundle = new Bundle();
        bundle.putInt(EDUCATE_TYPE, lableId);
        EducationOnlineFragment fragment = new EducationOnlineFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void freshlayoutOnLoadMore() {

    }

    @Override
    protected void freshlayoutOnRefresh() {
        requestData();
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new EducateAdapter(R.layout.educate_item);
    }

    @Override
    protected HomePagePresent createPresenter() {
        return new HomePagePresent();
    }

    @Override
    protected void lazyLoad() {
        lableId = getArguments().getInt(EDUCATE_TYPE, 0);
        ((EducateOnlineActivity) getActivity()).setOnSearchCallBack(new EducateOnlineActivity.OnSearchCallBack() {
            @Override
            public void startSearch() {
                requestData();
            }
        });
        requestData();
    }

    @Override
    protected void initView() {
        isLinearLayout = false;
        super.initView();
    }

    @Override
    protected void initData() {
        mSmartrefreshlayout.setEnableLoadMore(false);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                // TODO: 2021-10-16 在线教育详情
                ToastUtils.toast(mContext, "点击了");
            }
        });
    }

    private void requestData() {
        FormBody.Builder builder = getBaseAppActivity().getBaseBuilder();
        String searchContent = ((EducateOnlineActivity) getActivity()).getSearchContent();
        if (!TextUtils.isEmpty(searchContent)) {
            builder.add("keyword", searchContent);
        }
        builder.add("labelId", String.valueOf(lableId));
        mPresenter.getEducationList(builder.build(), "");
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        if (o != null) {
            List<EducationListBean.DataBean> arrays = (List<EducationListBean.DataBean>) o;
            adapter.setNewData(arrays);
        }

    }
}
