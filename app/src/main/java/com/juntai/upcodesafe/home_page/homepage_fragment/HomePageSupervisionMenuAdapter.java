package com.juntai.upcodesafe.home_page.homepage_fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.HomeBusinessBean;
import com.juntai.upcodesafe.bean.HomePageMenuBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/18 15:39
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/18 15:39
 */
public class HomePageSupervisionMenuAdapter extends BaseQuickAdapter<HomeBusinessBean.DataBean, BaseViewHolder> {
    public HomePageSupervisionMenuAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBusinessBean.DataBean item) {
        helper.setText(R.id.supervision_menu_title_tv, item.getName());
        helper.setText(R.id.supervision_menu_amount_tv, String.valueOf(item.getTotal()));
    }
}
