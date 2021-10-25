package com.juntai.upcodesafe.home_page.educateOnline;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.NoticeBean;
import com.juntai.upcodesafe.utils.UrlFormatUtil;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-10-16 16:23
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-16 16:23
 */
public class EducateAdapter extends BaseQuickAdapter<NoticeBean.DataBean, BaseViewHolder> {
    public EducateAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoticeBean.DataBean item) {
        helper.setText(R.id.educate_item_title_tv, item.getTitle());
        ImageLoadUtil.loadImage(mContext, UrlFormatUtil.getImageOriginalUrl(item.getCoverPicture()),helper.getView(R.id.educate_coverpic_iv));
    }
}
