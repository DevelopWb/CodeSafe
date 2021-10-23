package com.juntai.upcodesafe.home_page.unit;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.DisplayUtil;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.IdNameBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-10-22 15:30
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-22 15:30
 */
public class ManagerListAdapter extends BaseQuickAdapter<IdNameBean.DataBean, BaseViewHolder> {
    public ManagerListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, IdNameBean.DataBean item) {
        TextView textView = helper.getView(R.id.single_text_tv);
        helper.setText(R.id.single_text_tv,item.getName());
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) textView.getLayoutParams();
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        textView.setLayoutParams(params);
        textView.setPadding(DisplayUtil.dp2px(mContext,20),0,DisplayUtil.dp2px(mContext,5),DisplayUtil.dp2px(mContext,5));
    }
}
