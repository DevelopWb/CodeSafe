package com.juntai.upcodesafe.mine.addInformation.addUnit;

import android.content.DialogInterface;
import android.text.TextUtils;


import com.juntai.disabled.basecomponent.utils.DialogUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.bean.UnitDetailBean;
import com.juntai.upcodesafe.home_page.baseinspect.BaseCommitFootViewActivity;
import com.orhanobut.hawk.Hawk;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  单位
 * @date 2021/5/7 11:30
 */
public abstract class BaseAddUnitActivity extends BaseCommitFootViewActivity {

    public UnitDetailBean.DataBean bean;
    private UnitDetailBean.DataBean savedUnitBean;
    public int unitId;

    @Override
    public void initData() {
        unSavedLogic();
        savedUnitBean = Hawk.get(getHawkKey());
        if (savedUnitBean != null) {
            setAlertDialogHeightWidth(DialogUtil.getDialog(mContext).setMessage("您上次还有未提交的草稿,是否进入草稿？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.setNewData(mPresenter.getUnitInfoData(savedUnitBean, false));
                        }
                    }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startLocation();
                        }
                    }).show(), -1, 0);
        }

    }

    /**
     * 获取key
     *
     * @return
     */
    protected abstract String getHawkKey();

    /**
     * 未保存草稿的逻辑
     */
    private void unSavedLogic() {
        if (getIntent() != null) {
            bean = getIntent().getParcelableExtra(PARCELABLE_KEY);
            if (bean != null) {
                unitId = bean.getId();
            }
            adapter.setNewData(mPresenter.getUnitInfoData(bean, false));
        }
    }

    @Override
    public boolean requestLocation() {
        if (savedUnitBean != null && !TextUtils.isEmpty(savedUnitBean.getGpsAddress()) || bean != null && !TextUtils.isEmpty(bean.getGpsAddress())) {
            return false;
        }
        return true;
    }



    @Override
    protected void saveDraft() {
        //保存草稿
        if (getBaseAdapterData(true) != null) {
            Hawk.put(getHawkKey(), getBaseAdapterData(true).getUnitDataBean());
            ToastUtils.toast(mContext, "草稿保存成功");
            finish();
        }
    }


    @Override
    protected void commitRequest(MultipartBody.Builder builder) {
        commit(builder);

    }


    protected abstract void commit(MultipartBody.Builder builder);


    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        switch (tag) {
            case AppHttpPath.MANUAL_ADD_UNIT:
                ToastUtils.toast(mContext, "添加成功");
                if (Hawk.contains(getHawkKey())) {
                    Hawk.delete(getHawkKey());
                }
                finish();
                break;
            default:
                break;
        }
    }

}
