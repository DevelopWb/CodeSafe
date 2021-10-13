package com.juntai.upcodesafe.entrance;

import com.juntai.disabled.basecomponent.mvp.IPresenter;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.BaseAppFragment;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-10-10 11:07
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-10 11:07
 */
public class EmptyFragment extends BaseAppFragment {
    @Override
    protected IPresenter createPresenter() {
        return null;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.recycleview_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(String tag, Object o) {

    }
}
