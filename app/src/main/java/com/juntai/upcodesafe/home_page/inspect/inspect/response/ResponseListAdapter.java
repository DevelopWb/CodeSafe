package com.juntai.upcodesafe.home_page.inspect.inspect.response;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.IdNameBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-10-09 11:30
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-09 11:30
 */
public class ResponseListAdapter extends BaseQuickAdapter<IdNameBean.DataBean, BaseViewHolder> {
    public ResponseListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, IdNameBean.DataBean item) {
        helper.setText(R.id.notice_content_tv, item.getName());
            ImageLoadUtil.loadImageCache(mContext,R.mipmap.unit_icon,helper.getView(R.id.notice_iv));
    }
}
