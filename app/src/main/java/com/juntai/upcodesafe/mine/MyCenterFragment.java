package com.juntai.upcodesafe.mine;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.BaseAppFragment;
import com.juntai.upcodesafe.bean.MultipleItem;
import com.juntai.upcodesafe.bean.MyMenuBean;
import com.juntai.upcodesafe.entrance.LoginActivity;
import com.juntai.upcodesafe.utils.HawkProperty;
import com.juntai.upcodesafe.utils.UserInfoManager;
import com.orhanobut.hawk.Hawk;

/**
 * @aouther tobato
 * @description 描述
 * @date 2021/4/17 16:12
 */
public class MyCenterFragment extends BaseAppFragment<MyCenterPresent> implements MyCenterContract.ICenterView, View.OnClickListener {

    MyMenuAdapter myMenuAdapter;

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
    private AlertDialog dialog;

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
        mHeadImage.setImageResource(R.mipmap.default_user_head_icon);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (UserInfoManager.isLogin()) {
            mLoginOut.setVisibility(View.VISIBLE);
            // TODO: 2021-10-10 获取用户基本信息的接口
//            mPresenter.getUserInfo(getBaseAppActivity().getBaseBuilder().build(), AppHttpPath.GET_USER_INFO);
        } else {
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
                dialog = getBaseActivity().setAlertDialogHeightWidth( DialogUtil.getMessageDialog(mContext, "是否退出登录", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         //  调用退出登录接口
                         mPresenter.logout(getBaseAppActivity().getBaseBuilder().build(), AppHttpPath.LOGOUT);

                     }
                 }).show(),-1,0);
                break;
        }
    }

    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case AppHttpPath.LOGOUT:
                dialog.dismiss();
                ToastUtils.success(mContext, "退出成功");
                Hawk.delete(HawkProperty.LOGIN_KEY);
                Hawk.delete(HawkProperty.TOKEN_KEY);
                startActivity(new Intent(mContext, LoginActivity.class));

//                //重置界面
//                mNickname.setText("点击登录");
//                mNickname.setAlpha(0.3f);
//                mTelNumber.setVisibility(View.GONE);
//                mLoginOut.setVisibility(View.GONE);
//                mHeadImage.setImageResource(R.mipmap.default_user_head_icon);
                break;
            default:
                break;
        }
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
