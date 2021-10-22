package com.juntai.upcodesafe.home_page.unit;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
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
        helper.setText(R.id.single_text_tv,item.getName());
    }
}
