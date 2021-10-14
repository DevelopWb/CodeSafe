package com.juntai.upcodesafe.home_page.enterprise.educateOnline;

import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.mvp.IPresenter;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.BaseRecyclerviewFragment;
import com.juntai.upcodesafe.home_page.enterprise.notice.EnterpriseNoticeAdapter;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-10-09 14:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-09 14:55
 */
public class EducationOnlineFragment extends BaseRecyclerviewFragment {
    public static String EDUCATE_TYPE = "educatetype";
    public static String EDUCATE_TAB_PROFESSIONAL = "专项培训";
    public static String EDUCATE_TAB_SELFL = "自主培训";
    public static String EDUCATE_TAB_IMPORTANT_WORK = "重点岗位培训";
    public static String EDUCATE_TAB_SAFE_TRAIN = "安全考试";

    public static EducationOnlineFragment getInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString(EDUCATE_TYPE, type);
        EducationOnlineFragment fragment = new EducationOnlineFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void freshlayoutOnLoadMore() {

    }

    @Override
    protected void freshlayoutOnRefresh() {

    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new EnterpriseNoticeAdapter(R.layout.notice_item);
    }

    @Override
    protected IPresenter createPresenter() {
        return null;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void initData() {
        adapter.setNewData(getBaseActivity().getTestData());
    }
}
