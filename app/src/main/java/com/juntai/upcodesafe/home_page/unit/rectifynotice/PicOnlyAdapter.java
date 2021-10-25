package com.juntai.upcodesafe.home_page.unit.rectifynotice;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.utils.UrlFormatUtil;

/**
 * @aouther tobato
 * @description 描述  只展示图片的适配器
 * @date 2021-10-17 17:25
 */
public class PicOnlyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public PicOnlyAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageLoadUtil.loadImage(mContext, UrlFormatUtil.getImageOriginalUrl(item),helper.getView(R.id.pic_item_iv));
    }
}