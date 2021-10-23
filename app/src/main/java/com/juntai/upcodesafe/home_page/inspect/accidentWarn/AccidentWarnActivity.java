package com.juntai.upcodesafe.home_page.inspect.accidentWarn;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.video.player.PlayerActivity;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.BaseWebviewActivity;
import com.juntai.upcodesafe.bean.NoticeBean;
import com.juntai.upcodesafe.home_page.inspect.BaseSearchAndListActivity;
import com.juntai.upcodesafe.home_page.inspect.notice.EnterpriseNoticeAdapter;
import com.juntai.upcodesafe.utils.StringTools;

/**
 * @aouther tobato
 * @description 描述  事故警示
 * @date 2021-10-09 11:59
 */
public class AccidentWarnActivity extends BaseSearchAndListActivity {


    @Override
    public void initData() {
        super.initData();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NoticeBean.DataBean dataBean = (NoticeBean.DataBean) adapter.getData().get(position);
                int typeId = dataBean.getTypeId();
                if (1 == typeId) {
                    //类型id（1图文；2视频）
                    BaseWebviewActivity.startBaseWebviewActivity(mContext, BaseWebviewActivity.class, BaseWebviewActivity.WEB_CONTENT, dataBean.getContent(), "安全警示详情");
                } else {
                    if (!TextUtils.isEmpty(dataBean.getVideoUrl())) {
                        //   跳转到播放视频的界面
                        String playPath = AppHttpPath.BASE_IMAGE + dataBean.getVideoUrl();
                        //播放视频
                        Intent intent = new Intent(mContext, PlayerActivity.class);
                        intent.putExtra(PlayerActivity.VIDEO_PATH, playPath);
                        startActivity(intent);
                    }
                }

            }
        });
    }

    @Override
    protected void startSearch(String s) {
        if (TextUtils.isEmpty(s)) {
            mPresenter.getHomePageAccident(getBaseBuilder().build(), AppHttpPath.HOMEPAGE_NOTICE);
        } else {
            mPresenter.getHomePageAccident(getBaseBuilder().add("keyword", s).build(), AppHttpPath.HOMEPAGE_NOTICE);
        }
    }

    @Override
    protected String getTitleName() {
        return "事故警示";
    }


    @Override
    protected BaseQuickAdapter getAdapter() {
        return new EnterpriseNoticeAdapter(R.layout.notice_item);
    }
}
