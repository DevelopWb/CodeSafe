package com.juntai.upcodesafe.mine;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.base.BaseMvpFragment;
import com.juntai.disabled.basecomponent.utils.DialogUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.MultipleItem;
import com.juntai.upcodesafe.bean.MyMenuBean;
import com.juntai.upcodesafe.bean.UserBean;
import com.juntai.upcodesafe.utils.GridDividerItemDecoration;
import com.juntai.upcodesafe.utils.UserInfoManager;

/**
 * @aouther tobato
 * @description 描述
 * @date 2021/4/17 16:12
 */
public class MyCenterFragment extends BaseMvpFragment<MyCenterPresent> implements MyCenterContract.ICenterView, View.OnClickListener {

    private UserBean userBean;
    MyMenuAdapter myMenuAdapter;
    private String headUrl = "";

    private TextView mStatusTopTitle;
    private ImageView mHeadImage;
    private TextView mNickname;
    /**
     * 18763739973
     */
    private TextView mTelNumber;
    private RecyclerView mMenuRecycler;
    /**
     * 退出账号
     */
    private TextView mLoginOut;
    private int imUnReadCount;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_my_center;
    }

    @Override
    protected void initView() {
        mStatusTopTitle = getView(R.id.status_top_title);
        mHeadImage = getView(R.id.headImage);
        mHeadImage.setOnClickListener(this);
        mNickname = getView(R.id.nickname);
        mNickname.setAlpha(0.3f);
        mTelNumber = getView(R.id.tel_number);
        mTelNumber.setVisibility(View.GONE);
        mMenuRecycler = getView(R.id.menu_recycler);
        mLoginOut = getView(R.id.login_out);
        mLoginOut.setOnClickListener(this);
        myMenuAdapter = new MyMenuAdapter(mPresenter.getMenuBeans());
        getBaseActivity().initRecyclerview(mMenuRecycler,myMenuAdapter, LinearLayoutManager.VERTICAL);
        mStatusTopTitle.setText("个人中心");

        myMenuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MultipleItem multipleItem = (MultipleItem) adapter.getData().get(position);
                switch (multipleItem.getItemType()) {
                    case MultipleItem.ITEM_MENUS:
                        MyMenuBean myMenuBean = (MyMenuBean) multipleItem.getObject();
                        switch (myMenuBean.getTag()) {
                            case MyCenterContract.MENU_NEWS:
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.initList();
        mHeadImage.setImageResource(R.mipmap.default_user_head_icon);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (UserInfoManager.isLogin()){
            mLoginOut.setVisibility(View.VISIBLE);
        }else {
            mLoginOut.setVisibility(View.GONE);
        }
    }


    @Override
    protected void lazyLoad() {
    }

    @Override
    protected MyCenterPresent createPresenter() {
        return new MyCenterPresent();
    }


    @Override
    public void onClick(View v) {
        if (!UserInfoManager.isLogin()){
//            MyApp.goLogin();
            return;
        }
        switch (v.getId()) {
            case R.id.headImage:
                //用户信息设置
                break;
            case R.id.login_out:
                //退出登录
                DialogUtil.getMessageDialog(mContext, "是否退出登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
                break;
        }
    }

    @Override
    public void onSuccess(String tag, Object o) {
    }

    @Override
    public void onError(String tag, Object o) {
        ToastUtils.error(mContext, String.valueOf(o));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
