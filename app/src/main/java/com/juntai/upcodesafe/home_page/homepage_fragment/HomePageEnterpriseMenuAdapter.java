package com.juntai.upcodesafe.home_page.homepage_fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.HomePageMenuBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/18 15:39
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/18 15:39
 */
public class HomePageEnterpriseMenuAdapter extends BaseQuickAdapter<HomePageMenuBean, BaseViewHolder> {
    public HomePageEnterpriseMenuAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomePageMenuBean item) {

        helper.setText(R.id.homepage_menu_title_tv,item.getMenuName());
        helper.setText(R.id.homepage_menu_title_en_tv,item.getMenuEnName());
        ImageLoadUtil.loadImage(mContext,item.getMenuPicId(),helper.getView(R.id.menu_icon_iv));
    }
}
