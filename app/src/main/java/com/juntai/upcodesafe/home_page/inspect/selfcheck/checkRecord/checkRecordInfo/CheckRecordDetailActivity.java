package com.juntai.upcodesafe.home_page.inspect.selfcheck.checkRecord.checkRecordInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.CheckDetailBean;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.upcodesafe.utils.HawkProperty;

/**
 * @aouther tobato
 * @description 描述  检查记录详情
 * @date 2021-10-15 15:51
 */
public class CheckRecordDetailActivity extends BaseInspectionActivity {

    private PopupWindow popupWindow;
    private CheckDetailBean.DataBean dataBean;

    @Override
    public void initData() {
        getTitleRightTv().setText("添加");
        getTitleRightTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPop(getTitleRightTv());
            }
        });
        mPresenter.getCheckRecordDetail(getBaseBuilder().add("recordId",String.valueOf(baseId)).build(), AppHttpPath.CHECK_RECORD_DETAIL);
    }
    public void showPop(View view) {
        View viewPop = LayoutInflater.from(this).inflate(R.layout.check_detail_other_action, null);
        popupWindow = new PopupWindow(viewPop, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.showAsDropDown(view, 0, 10);//显示在控件下面
        viewPop.findViewById(R.id.add_punish_tv).setOnClickListener(v -> {
            startActivityForResult(new Intent(mContext,AddPunishActivity.class).putExtra(BASE_ID,
                    HawkProperty.getUnitBean().getId()).putExtra(BASE_ID2,dataBean.getId()),
                    BASE_REQUEST_RESULT);
            popupWindow.dismiss();
        });
        viewPop.findViewById(R.id.add_notce_tv).setOnClickListener(v -> {
            popupWindow.dismiss();
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isDetail = true;
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (BASE_REQUEST_RESULT==requestCode) {
            mPresenter.getCheckRecordDetail(getBaseBuilder().add("recordId",String.valueOf(baseId)).build(), AppHttpPath.CHECK_RECORD_DETAIL);

        }
    }
    @Override
    protected String getTitleName() {
        return "检查详情";
    }

    @Override
    protected View getFootView() {
        return null;
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        switch (tag) {
            case AppHttpPath.CHECK_RECORD_DETAIL:
                CheckDetailBean checkDetailBean = (CheckDetailBean) o;
                if (checkDetailBean != null) {
                    dataBean = checkDetailBean.getData();
                    adapter.setNewData(mPresenter.getCheckedDetailData(dataBean));
                }

                break;
            default:
                break;
        }

    }
}
