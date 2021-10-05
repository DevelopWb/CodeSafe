package com.juntai.upcodesafe.home_page.baseinspect;

import android.view.Gravity;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.TextKeyValueBean;


public class TextKeyValueAdapter extends BaseQuickAdapter<TextKeyValueBean, BaseViewHolder> {


    public TextKeyValueAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TextKeyValueBean item) {
        helper.setText(R.id.item_text_key,item.getKey());
        helper.setText(R.id.item_text_value,item.getValue());
        TextView keyTv = helper.getView(R.id.item_text_key);
        TextView valueTv = helper.getView(R.id.item_text_value);
        if (item.isValueGravityToRight()) {
            valueTv.setGravity(Gravity.RIGHT);
        }else {
            valueTv.setGravity(Gravity.LEFT);
        }
    }
}