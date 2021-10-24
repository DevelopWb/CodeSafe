package com.juntai.upcodesafe.home_page.inspect.notice;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.NoticeBean;
import com.juntai.upcodesafe.utils.UrlFormatUtil;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-10-09 11:30
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-09 11:30
 */
public class EnterpriseNoticeAdapter extends BaseQuickAdapter<NoticeBean.DataBean, BaseViewHolder> {
    public EnterpriseNoticeAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoticeBean.DataBean item) {
        helper.setText(R.id.notice_content_tv, item.getTitle());
        if (TextUtils.isEmpty(item.getCoverPicture())) {
            ImageLoadUtil.loadImageCache(mContext,R.mipmap.message_icon,helper.getView(R.id.notice_iv));
        }else {
            ImageLoadUtil.loadImageCache(mContext, UrlFormatUtil.getImageOriginalUrl(item.getCoverPicture()),helper.getView(R.id.notice_iv));

        }
    }
}
