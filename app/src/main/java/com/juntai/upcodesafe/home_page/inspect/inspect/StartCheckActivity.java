package com.juntai.upcodesafe.home_page.inspect.inspect;

import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.juntai.disabled.basecomponent.utils.DialogUtil;
import com.juntai.disabled.basecomponent.utils.GsonTools;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.BaseAdapterDataBean;
import com.juntai.upcodesafe.bean.CheckDetailBean;
import com.juntai.upcodesafe.bean.TextKeyValueBean;
import com.juntai.upcodesafe.bean.UnitDetailBean;
import com.juntai.upcodesafe.home_page.baseinspect.BaseCommitFootViewActivity;
import com.juntai.upcodesafe.home_page.baseinspect.TextKeyValueAdapter;
import com.juntai.upcodesafe.utils.HawkProperty;
import com.juntai.upcodesafe.utils.UserInfoManager;
import com.orhanobut.hawk.Hawk;

import java.util.List;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  企业自查
 * @date 2021-10-14 10:28
 */
public class StartCheckActivity extends BaseCommitFootViewActivity {
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
        HawkProperty.UPLOAD_TYPE = 1;
        unSavedLogic();
        List<CheckDetailBean.DataBean.ConcreteProblemsBean> arrays = Hawk.get(HawkProperty.START_CHECK + unitBean.getId());
        if (arrays!=null&&!arrays.isEmpty()) {
            setAlertDialogHeightWidth(DialogUtil.getDialog(mContext).setMessage("您上次还有未提交的草稿,是否进入草稿？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.setNewData(mPresenter.showSavedDesPics(arrays));
                        }
                    }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mRadioQualifiedRb.setChecked(true);
                            startLocation();
                        }
                    }).show(), -1, 0);
        }

    }

    /**
     * 未保存草稿的逻辑
     */
    private void unSavedLogic() {

        if (getIntent() != null) {
            unitBean = getIntent().getParcelableExtra(PARCELABLE_KEY);
            adapter.setHeaderView(getHeadView());
            adapter.setNewData(mPresenter.addDesPicLayout("检查情况描述", "上传检查图片", 0));
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
        //保存草稿
        BaseAdapterDataBean adapterDataBean = getBaseAdapterData(true);
        if (adapterDataBean != null) {
            if (!adapterDataBean.getProblems().isEmpty()) {
                Hawk.put(HawkProperty.START_CHECK + unitBean.getId(), adapterDataBean.getProblems());
            }
            Hawk.put(HawkProperty.START_CHECK_ISOK + unitBean.getId(), mRadioQualifiedRb.isChecked());
            ToastUtils.toast(mContext, "草稿保存成功");
            finish();
        }
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
        if (Hawk.get(HawkProperty.START_CHECK_ISOK+unitBean.getId(),true)) {
            mRadioQualifiedRb.setChecked(true);
        }else {
            mRadioUnqualifiedRb.setChecked(true);
        }
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
        switch (tag) {
            case AppHttpPath.START_INSPECT:
                Hawk.delete(HawkProperty.START_CHECK + unitBean.getId());
                Hawk.delete(HawkProperty.START_CHECK_ISOK + unitBean.getId());
                ToastUtils.toast(mContext, "提交成功");
                finish();
                break;
            default:
                break;
        }

    }
}
