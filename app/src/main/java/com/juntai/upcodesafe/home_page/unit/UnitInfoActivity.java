package com.juntai.upcodesafe.home_page.unit;

import android.content.Intent;

import com.baidu.mapapi.model.LatLng;
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

import okhttp3.FormBody;

/**
 * @aouther tobato
 * @description 描述  单位信息
 * @date 2021-10-08 14:40
 */
public class UnitInfoActivity extends BaseInspectionInfoActivity {

    private UnitDetailBean.DataBean enterPriseBean;

    @Override
    public void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getIntent() != null) {
            String  stringType = getIntent().getStringExtra(BaseInspectionInfoActivity.BASE_STRING);
            if (stringType == null) {
                return;
            }
            FormBody.Builder builder = getBaseBuilder();
            if (contentId > 0) {
                builder.add("messageId", String.valueOf(contentId));
            }
            switch (stringType) {

                case BaseInspectionInfoActivity.BASE_STRING_VALUE1:
                    //企业明细
                    mPresenter.getEnterpriseInfo(builder.add("unitId", String.valueOf(baseId)).build(), AppHttpPath.GET_ENTERPRIZSE_INFO);
                    break;
                case BaseInspectionInfoActivity.BASE_STRING_VALUE2:
                    //企业自身明细
                    mPresenter.getEnterpriseInfo(builder.add("unitId", String.valueOf(UserInfoManager.getUnitId())).build(), AppHttpPath.GET_ENTERPRIZSE_INFO);
                    break;
                case BaseInspectionInfoActivity.BASE_STRING_VALUE3:
                    //通过uuid查询企业明细
                    mPresenter.getEnterpriseInfoByUUID(builder.add("uuid",String.valueOf(baseId)).build(), AppHttpPath.GET_ENTERPRIZSE_INFO);
                    break;
                default:
                    break;
            }

        }
    }

    @Override
    protected Object getBaseBean() {
        return enterPriseBean;
    }

    @Override
    protected List<ActionBean> getActionAdapterData() {
        List<ActionBean> arrays = new ArrayList<>();
        arrays.add(new ActionBean(R.mipmap.action_bg_yellow, BaseInspectContract.RESPONSE_LIST,
                R.mipmap.action_response_icon));
        arrays.add(new ActionBean(R.mipmap.action_bg, BaseInspectContract.CHECK_RECORD,
                R.mipmap.action_check_record));
        arrays.add(new ActionBean(R.mipmap.action_bg, BaseInspectContract.REPAIRE_NOTICE,
                R.mipmap.action_rectifynotice));
        arrays.add(new ActionBean(R.mipmap.action_bg, BaseInspectContract.TRAIN_PLAN,
                R.mipmap.action_train_plan));
        return arrays;
    }

    @Override
    protected String getTitleName() {
        if (UserInfoManager.getAccountTypeId()==4) {
            return "企业自查";
        }
        return "单位详情";
    }

    @Override
    protected String getStartWorkName() {
        if (UserInfoManager.getAccountTypeId()==4) {
            return "开始自查";
        }
        return "开始检查";
    }

    @Override
    protected String getQrCodePath() {
        return UrlFormatUtil.getImageOriginalUrl(enterPriseBean.getQrCode());
    }

    @Override
    protected void navigationLogic() {
        if (enterPriseBean != null) {
            navigationLogic(new LatLng(Double.parseDouble(enterPriseBean.getLatitude()),
                    Double.parseDouble(enterPriseBean.getLongitude())), enterPriseBean.getGpsAddress());
        }
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
        arrays.add(new TextKeyValueBean("单位法人:", dataBean.getLegal()));
        return arrays;
    }


}
