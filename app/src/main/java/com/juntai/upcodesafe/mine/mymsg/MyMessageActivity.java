package com.juntai.upcodesafe.mine.mymsg;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.BaseRecyclerviewActivity;
import com.juntai.upcodesafe.bean.MyMsgBean;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectionInfoActivity;
import com.juntai.upcodesafe.home_page.inspect.inspect.UnitInfoActivity;
import com.juntai.upcodesafe.mine.MyCenterContract;
import com.juntai.upcodesafe.mine.MyCenterPresent;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述
 * @date 2021/6/3 11:28
 */
public class MyMessageActivity extends BaseRecyclerviewActivity<MyCenterPresent> implements MyCenterContract.ICenterView {

    @Override
    protected MyCenterPresent createPresenter() {
        return new MyCenterPresent();
    }

    @Override
    public void initData() {
        setTitleName("我的消息");
        mSmartrefreshlayout.setEnableRefresh(false);
        mSmartrefreshlayout.setEnableLoadMore(false);
        mPresenter.getMyMsgs(getBaseBuilder().build(), "");
        addDivider(true, mRecyclerview, false, true);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyMsgBean.DataBean dataBean = (MyMsgBean.DataBean) adapter.getData().get(position);
                int msgId = dataBean.getId();
//                contentId  对应单位详情里面的unitId
                int contentId = dataBean.getContentId();
                startActivityForResult(new Intent(mContext, UnitInfoActivity.class)
                                        .putExtra(BaseInspectionInfoActivity.BASE_ID2, msgId)
                                        .putExtra(BaseInspectionInfoActivity.BASE_ID, contentId),
                                BASE_REQUEST_RESULT);

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.getMyMsgs(getBaseBuilder().build(), "");
    }

    @Override
    protected void freshlayoutOnLoadMore() {

    }

    @Override
    protected void freshlayoutOnRefresh() {

    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new MyMsgAdapter(R.layout.item_mine_msg);
    }

    @Override
    public void onSuccess(String tag, Object o) {
        MyMsgBean myMsgBean = (MyMsgBean) o;
        if (myMsgBean != null) {
            List<MyMsgBean.DataBean> arrays = myMsgBean.getData();
            adapter.setNewData(arrays);
        }
    }
}
