package com.juntai.upcodesafe.home_page.unit;

import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;

import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.utils.DialogUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.bean.IdNameBean;
import com.juntai.upcodesafe.bean.TextKeyValueBean;
import com.juntai.upcodesafe.bean.UnitDetailBean;
import com.juntai.upcodesafe.home_page.baseinspect.BaseCommitFootViewActivity;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectContract;
import com.juntai.upcodesafe.utils.HawkProperty;
import com.orhanobut.hawk.Hawk;

import java.util.List;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  单位
 * @date 2021/5/7 11:30
 */
public abstract class BaseAddUnitActivity extends BaseCommitFootViewActivity {

    private boolean isUnitNameUnque = false;//单位名称是否唯一
    private boolean isUnitUCCUnique = false;//社会信用代码是否唯一
    public UnitDetailBean.DataBean bean;
    private UnitDetailBean.DataBean savedUnitBean;
    public int unitId;
//    public int version;

    @Override
    public void initData() {
        if (!isAddInfo()) {
            //  获取行业类型
            mPresenter.getBusinessTypes(getBaseBuilder().build(), AppHttpPath.GET_BUSINESS_TYPES);
        }

        unSavedLogic();
        savedUnitBean = Hawk.get(getHawkKey());
        if (savedUnitBean != null) {
            setAlertDialogHeightWidth(DialogUtil.getDialog(mContext).setMessage("您上次还有未提交的草稿,是否进入草稿？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            initStatus(savedUnitBean);
                            adapter.setNewData(mPresenter.getUnitInfoData(savedUnitBean, false, isAddInfo()));
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
     * 是在补充资料
     *
     * @return
     */
    protected abstract boolean isAddInfo();

    private void initStatus(UnitDetailBean.DataBean bean) {
        if (!TextUtils.isEmpty(bean.getName())) {
            isUnitNameUnque = true;
        }
        if (!TextUtils.isEmpty(bean.getUnifiedCreditCode())) {
            isUnitUCCUnique = true;
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
//                version = bean.getVersion();
                initStatus(bean);
            }
            adapter.setNewData(mPresenter.getUnitInfoData(bean, false, isAddInfo()));
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
        if (!isUnitNameUnque) {
            ToastUtils.toast(mContext, "单位名称已存在,不可重复添加");
            return;
        }
        if (!isUnitUCCUnique) {
            ToastUtils.toast(mContext, "社会信用代码已存在,不可重复添加");
            return;
        }
        commit(builder);

    }


    protected abstract void commit(MultipartBody.Builder builder);


    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);

        switch (tag) {
            case BaseInspectContract.INSPECTION_UNIT_NAME:
                BaseResult result = (BaseResult) o;
                if ("成功".equals(result.message)) {
                    isUnitNameUnque = true;
                } else {
                    ToastUtils.toast(mContext, "单位名称已存在");
                    isUnitNameUnque = false;
                }
                break;
            case BaseInspectContract.INSPECTION_UNIT_UCC:
                BaseResult baseResult = (BaseResult) o;
                if ("成功".equals(baseResult.message)) {
                    isUnitUCCUnique = true;
                } else {
                    ToastUtils.toast(mContext, "社会信用代码已存在");
                    isUnitUCCUnique = false;
                }
                break;
            case AppHttpPath.MANUAL_ADD_UNIT:
            case AppHttpPath.ADD_UNIT:
                ToastUtils.toast(mContext, "添加成功");
                if (Hawk.contains(getHawkKey())) {
                    Hawk.delete(getHawkKey());
                }
                finish();
                break;
            case AppHttpPath.GET_BUSINESS_TYPES:
                IdNameBean idNameBean = (IdNameBean) o;
                if (idNameBean != null) {
                    List<IdNameBean.DataBean> arrays = idNameBean.getData();
                    Hawk.put(HawkProperty.BUSINESS_TYPES_KEY, arrays);
                }
                break;
//            case AppHttpPath.ADD_UNIT:
//                ToastUtils.toast(mContext, "添加成功");
//                finish();
//                break;
            case AppHttpPath.EDIT_UNIT_INFO:
                ToastUtils.toast(mContext, "提交成功");
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
