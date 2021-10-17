package com.juntai.upcodesafe.home_page.inspect.selfcheck;

import android.content.Intent;

import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.ActionBean;
import com.juntai.upcodesafe.bean.TextKeyValueBean;
import com.juntai.upcodesafe.bean.UnitDetailBean;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectContract;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectionInfoActivity;
import com.juntai.upcodesafe.utils.HawkProperty;
import com.juntai.upcodesafe.utils.UrlFormatUtil;
import com.juntai.upcodesafe.utils.UserInfoManager;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述  单位信息
 * @date 2021-10-08 14:40
 */
public class UnitInfoActivity extends BaseInspectionInfoActivity {

    private UnitDetailBean.DataBean enterPriseBean;

    @Override
    public void initData() {
        if (getIntent() != null) {
            String uuid = getIntent().getStringExtra(BASE_ID);
            mPresenter.getEnterpriseInfoByUUID(getBaseBuilder().add("uuid",uuid).build(), AppHttpPath.GET_ENTERPRIZSE_INFO);
        }else {
            mPresenter.getEnterpriseInfo(getBaseBuilder().add("unitId", String.valueOf(UserInfoManager.getUnitId())).build(), AppHttpPath.GET_ENTERPRIZSE_INFO);

        }
    }


    @Override
    protected Object getBaseBean() {
        return enterPriseBean;
    }

    @Override
    protected List<ActionBean> getActionAdapterData() {
        List<ActionBean> arrays = new ArrayList<>();
        arrays.add(new ActionBean(R.mipmap.action_bg, BaseInspectContract.RESPONSE_LIST,
                R.mipmap.action_check_record));
        arrays.add(new ActionBean(R.mipmap.action_bg, BaseInspectContract.CHECK_RECORD,
                R.mipmap.action_check_record));
        arrays.add(new ActionBean(R.mipmap.action_bg, BaseInspectContract.REPAIRE_NOTICE,
                R.mipmap.action_check_record));
        arrays.add(new ActionBean(R.mipmap.action_bg, BaseInspectContract.TRAIN_PLAN,
                R.mipmap.action_check_record));
        return arrays;
    }

    @Override
    protected String getTitleName() {
        return "企业自查";
    }

    @Override
    protected String getStartWorkName() {
        return "开始自查";
    }

    @Override
    protected String getQrCodePath() {
        return null;
    }

    @Override
    protected void navigationLogic() {

    }

    @Override
    protected void seeMoreInfo() {
        startActivity(new Intent(mContext, UnitInfoMoreActivity.class).putExtra(BaseInspectionActivity.PARCELABLE_KEY
                , enterPriseBean));
    }
    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case AppHttpPath.GET_ENTERPRIZSE_INFO:
                enterPriseBean = (UnitDetailBean.DataBean) o;
                Hawk.put(HawkProperty.UNIT_KEY,enterPriseBean);
                if (enterPriseBean != null) {
                    baseInfoAdapter.setNewData(getData(enterPriseBean));
                    ImageLoadUtil.loadImage(mContext, UrlFormatUtil.getImageOriginalUrl(enterPriseBean.getQrCode()), mQrCodeIv);
                }
                break;
            default:
                break;
        }
    }

    private List<TextKeyValueBean> getData(UnitDetailBean.DataBean dataBean) {
        List<TextKeyValueBean> arrays = new ArrayList<>();
        arrays.add(new TextKeyValueBean("单位名称:", dataBean.getName()));
        arrays.add(new TextKeyValueBean("单位地址:", dataBean.getAddress()));
        arrays.add(new TextKeyValueBean("单位法人:", dataBean.getPersonLiable()));
        return arrays;
    }


}
