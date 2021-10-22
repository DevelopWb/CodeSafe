package com.juntai.upcodesafe.mine.mymsg;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.MyMsgBean;

/**
 * @aouther tobato
 * @description 描述
 * @date 2021/6/3 11:43
 */
public class MyMsgAdapter extends BaseQuickAdapter<MyMsgBean.DataBean, BaseViewHolder> {


    public MyMsgAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyMsgBean.DataBean item) {

        helper.setText(R.id.msg_content_tv, item.getContentName());
        helper.setText(R.id.msg_time_tv, item.getPublishTime());

        if (0==item.getIsRead()) {
            //未读
            helper.setGone(R.id.unread_tag_tv,true);
        }else {
            helper.setGone(R.id.unread_tag_tv,false);

        }
    }

}
