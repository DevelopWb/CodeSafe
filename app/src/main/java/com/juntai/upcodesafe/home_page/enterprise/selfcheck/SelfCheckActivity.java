package com.juntai.upcodesafe.home_page.enterprise.selfcheck;

import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.ActionBean;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectContract;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectionInfoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述  企业自查
 * @date 2021-10-08 14:40
 */
public class SelfCheckActivity extends BaseInspectionInfoActivity {

    @Override
    public void initData() {

    }


    @Override
    protected Object getBaseBean() {
        return null;
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

    }

    @Override
    public void onSuccess(String tag, Object o) {

    }
}
