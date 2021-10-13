package com.juntai.upcodesafe.mine.addInformation;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.UnitsBean;
import com.juntai.upcodesafe.utils.UrlFormatUtil;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-10-10 17:17
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-10 17:17
 */
public class BindUnitAdapter extends BaseQuickAdapter<UnitsBean.DataBean, BaseViewHolder> {
    public BindUnitAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, UnitsBean.DataBean item) {

        helper.setText(R.id.item_title_tv,item.getName());
        helper.setText(R.id.item_navigation_tv,"绑定");
//        helper.addOnClickListener(R.id.item_navigation_tv);
        helper.setText(R.id.item_des_tv,item.getAddress());
        if (TextUtils.isEmpty(item.getAddress())) {
            helper.setGone(R.id.item_des_tv,false);
        }else {
            helper.setGone(R.id.item_des_tv,true);
        }
        if (!TextUtils.isEmpty(item.getLogo())) {
            ImageLoadUtil.loadImage(mContext, UrlFormatUtil.getImageOriginalUrl(item.getLogo()),helper.getView(R.id.cover_pic_iv));
        }

    }
}
