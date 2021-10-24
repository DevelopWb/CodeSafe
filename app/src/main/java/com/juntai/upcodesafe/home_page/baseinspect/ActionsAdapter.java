package com.juntai.upcodesafe.home_page.baseinspect;

import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.ActionBean;

/**
 * @aouther tobato
 * @description 描述
 * @date 2021/4/29 16:24
 */

public class ActionsAdapter extends BaseQuickAdapter<ActionBean, BaseViewHolder> {


    public ActionsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ActionBean item) {
        ConstraintLayout  bgCl = helper.getView(R.id.actions_bg_cl);
        bgCl.setBackgroundResource(item.getBgRes());
        ImageLoadUtil.loadImage(mContext,item.getActionPic(),helper.getView(R.id.item_pic_iv));
        helper.setText(R.id.item_title_tv,item.getActionName());
        if (BaseInspectContract.RESPONSE_LIST.equals(item.getActionName())) {
            helper.setTextColor(R.id.item_title_tv,ContextCompat.getColor(mContext,R.color.colorOrange));
            helper.setTextColor(R.id.enter_tv,ContextCompat.getColor(mContext,R.color.colorOrange));
            helper.setImageResource(R.id.enter_tag_iv,R.mipmap.arrows_right_orange);
        }else {
            helper.setTextColor(R.id.item_title_tv,ContextCompat.getColor(mContext,R.color.colorAccent));
            helper.setTextColor(R.id.enter_tv,ContextCompat.getColor(mContext,R.color.colorAccent));
            helper.setImageResource(R.id.enter_tag_iv,R.mipmap.arrows_right_coloraccent);

        }
    }
}