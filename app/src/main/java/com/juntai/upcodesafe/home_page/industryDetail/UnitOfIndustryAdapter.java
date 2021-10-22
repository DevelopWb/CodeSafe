package com.juntai.upcodesafe.home_page.industryDetail;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.UnitOfInductryBean;
import com.juntai.upcodesafe.utils.UrlFormatUtil;

/**
 * @aouther tobato
 * @description 描述  行业详情里面的企业
 * @date 2021/6/1 16:16
 */

public class UnitOfIndustryAdapter extends BaseQuickAdapter<UnitOfInductryBean.DataBean, BaseViewHolder> {


    public UnitOfIndustryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, UnitOfInductryBean.DataBean item) {
        helper.addOnClickListener(R.id.item_navigation_tv);
        helper.setText(R.id.item_title_tv,item.getName());
        helper.setText(R.id.item_des_tv,item.getAddress());
        helper.setText(R.id.unit_nature_tv,item.getTypeName());
        helper.setText(R.id.unit_quality_tv,1==item.getQualified()?"合格":"不合格");
        ImageLoadUtil.loadImage(mContext, UrlFormatUtil.getImageOriginalUrl(item.getPicture()),helper.getView(R.id.cover_pic_iv));
    }

}