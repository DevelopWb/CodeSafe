package com.juntai.upcodesafe.home_page.inspect.inspect;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.TextKeyValueBean;
import com.juntai.upcodesafe.bean.UnitDetailBean;
import com.juntai.upcodesafe.home_page.baseinspect.BaseCommitFootViewActivity;
import com.juntai.upcodesafe.home_page.baseinspect.TextKeyValueAdapter;
import com.juntai.upcodesafe.utils.HawkProperty;
import com.juntai.upcodesafe.utils.UserInfoManager;
import com.orhanobut.hawk.Hawk;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  企业自查
 * @date 2021-10-14 10:28
 */
public class StartCheckSelfActivity extends BaseCommitFootViewActivity {
    private RecyclerView mFireCheckHeadRv;
    /**
     * 合格
     */
    private RadioButton mRadioQualifiedRb;
    /**
     * 不合格
     */
    private RadioButton mRadioUnqualifiedRb;
    private RadioGroup mItemRadioG;
    private TextKeyValueAdapter headAdapter;
    private UnitDetailBean.DataBean unitBean;

    @Override
    public void initData() {
        HawkProperty.UPLOAD_TYPE =  1;

        adapter.setHeaderView(getHeadView());
        adapter.setNewData(mPresenter.addDesPicLayout("检查情况描述", "上传检查图片", 0));
        if (getIntent() != null) {
            unitBean = getIntent().getParcelableExtra(PARCELABLE_KEY);
            if (unitBean != null) {
                headAdapter.setNewData(mPresenter.getStartCheckData(unitBean));
            }
        }
    }


    @Override
    protected String getCommitTextValue() {
        return "提交";
    }

    @Override
    protected void commitRequest(MultipartBody.Builder builder) {
        MultipartBody.Builder body = builder
                .addFormDataPart("unitId", String.valueOf(unitBean.getId()))
                .addFormDataPart("checkTime", ((TextKeyValueBean) headAdapter.getData().get(0)).getValue())
                .addFormDataPart("checkType", String.valueOf(UserInfoManager.getCheckType()))
                .addFormDataPart("userId", String.valueOf(UserInfoManager.getUserId()))
                .addFormDataPart("personLiable", unitBean.getPersonLiable())
                .addFormDataPart("phoneNumber", unitBean.getLiablePhone())
                .addFormDataPart("qualified", mRadioQualifiedRb.isChecked() ? "1" : "2");

        if (UserInfoManager.getAccountTypeId() != 4) {
            //监管账号需要传部门id
            body.addFormDataPart("departmentId", String.valueOf(UserInfoManager.getDepartmentId()));
            //是否为验收检查（0不是；1是）
            body.addFormDataPart("acceptance", Hawk.get(HawkProperty.IS_YANSHOU_CHECK, false) ? "1" : "0");
        }
        if (UserInfoManager.getAccountTypeId() == 3 || UserInfoManager.getAccountTypeId() == 4) {
            //企业账户或者网格员账户 只有常规检查
            builder.addFormDataPart("typeId", "1");
        } else {
            boolean isNormalCheck = Hawk.get(HawkProperty.IS_NORMAL_CHECK, true);
            builder.addFormDataPart("typeId", isNormalCheck ? "1" : "2");
        }


        mPresenter.startInspect(body.build(), AppHttpPath.START_INSPECT);

    }


    @Override
    protected void saveDraft() {

    }

    @Override
    protected String getTitleName() {
        if (UserInfoManager.getAccountTypeId() == 4) {
            return "开始自查";
        }
        return "开始检查";
    }

    /**
     * 头布局
     *
     * @return
     */
    private View getHeadView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fire_check_head_layout, null);
        mFireCheckHeadRv = (RecyclerView) view.findViewById(R.id.fire_check_head_rv);
        mRadioQualifiedRb = (RadioButton) view.findViewById(R.id.radio_qualified_rb);
        mRadioUnqualifiedRb = (RadioButton) view.findViewById(R.id.radio_unqualified_rb);
        mItemRadioG = (RadioGroup) view.findViewById(R.id.item_radio_g);
        headAdapter = new TextKeyValueAdapter(R.layout.text_key_value_item);
        initRecyclerview(mFireCheckHeadRv, headAdapter, LinearLayoutManager.VERTICAL);
        mItemRadioG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_qualified_rb:
                        //合格


                        break;
                    case R.id.radio_unqualified_rb:
                        //不合格
                        break;
                    default:
                        break;
                }
            }
        });
        return view;
    }


    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        ToastUtils.toast(mContext, "提交成功");
        finish();
    }
}
