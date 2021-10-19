package com.juntai.upcodesafe.home_page.unit;

import android.content.Intent;
import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.UnitDetailBean;
import com.juntai.upcodesafe.bean.UnitsBean;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.upcodesafe.utils.HawkProperty;
import com.juntai.upcodesafe.utils.UrlFormatUtil;
import com.juntai.upcodesafe.utils.UserInfoManager;
import com.orhanobut.hawk.Hawk;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述  添加单位
 * @date 2021/5/6 15:36
 */
public class AddUnitActivity extends BaseAddActivity {

    @Override
    public void initData() {
        startSearch("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onAdapterItemClick(BaseQuickAdapter adapter, int position) {
        UnitsBean.DataBean bean =
                (UnitsBean.DataBean) adapter.getData().get(position);
        if (0 == bean.getIsAdd()) {
            //未添加  搜索添加 需要将系统封面清空
//            bean.setLogo(null);
            //获取单位详情
            mPresenter.getEnterpriseInfo(getBaseBuilder().add("unitId", String.valueOf(bean.getId())).build(), AppHttpPath.GET_ENTERPRIZSE_INFO);
        } else {
            ToastUtils.toast(mContext, "该单位已添加");

        }


    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new UnitsAdapter(R.layout.check_item, false);
    }

    @Override
    protected void startSearch(String s) {
        mPresenter.searchAccountNature(getBaseBuilder().add("keyword", s).add("typeId", "4").build(), AppHttpPath.SEARCH_ACCOUNT_NATURE);

    }

    @Override
    protected String getTitleName() {
        return BaseInspectionActivity.ADD_UNIT;
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        switch (tag) {
            case AppHttpPath.SEARCH_ACCOUNT_NATURE:
                UnitsBean unitsBean = (UnitsBean) o;
                if (unitsBean != null) {
                    List<UnitsBean.DataBean> arrays = unitsBean.getData();
                    adapter.setNewData(arrays);
                    if (arrays.isEmpty()) {
                        ToastUtils.toast(mContext, "没有查到相关信息");
                    }
                }
                break;
            case AppHttpPath.GET_ENTERPRIZSE_INFO:
                UnitDetailBean.DataBean enterPriseBean = (UnitDetailBean.DataBean) o;
                startActivityForResult(new Intent(mContext, SearchAddUnitActivity.class).putExtra(BaseInspectionActivity.PARCELABLE_KEY, enterPriseBean), BASE_REQUEST_RESULT);

                break;
            default:
                break;
        }

    }
}
