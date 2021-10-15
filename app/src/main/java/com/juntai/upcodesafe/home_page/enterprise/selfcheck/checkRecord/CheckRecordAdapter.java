package com.juntai.upcodesafe.home_page.enterprise.selfcheck.checkRecord;


import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.CheckRecordBean;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectContract;

/**
 * @aouther tobato
 * @description 描述  工作记录
 * @date 2021/6/1 16:16
 */

public class CheckRecordAdapter extends BaseQuickAdapter<CheckRecordBean.DataBean, BaseViewHolder> {


    public CheckRecordAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CheckRecordBean.DataBean item) {
        helper.setText(R.id.record_time_tv, item.getCheckTime());
        helper.setGone(R.id.punish_tag_tv,item.getPunish()==1?true:false);
        helper.setText(R.id.check_big_tag_tv,getCheckName(item.getCheckType()));
        helper.setText(R.id.check_small_tag_tv,getSmallCheckName(item.getTypeId()));
        if (!TextUtils.isEmpty(item.getDepartmentName())) {
            helper.setGone(R.id.record_person_tv,true);
            helper.setText(R.id.record_person_tv,item.getDepartmentName());
        }else {
            helper.setGone(R.id.record_person_tv,false);
        }
        if (!TextUtils.isEmpty(item.getNickname())) {
            helper.setText(R.id.record_type_tv,item.getNickname());
        }else {
            helper.setText(R.id.record_type_tv,"检查记录");
        }
    }

    /**
     * 获取状态检查类型
     * 1企业自查；2监管检查；3属地检查；4网格检查
     *
     * @return
     */
    private String getCheckName(int checkType) {

        String checkName =null;
        switch (checkType) {
            case 1:
                checkName = BaseInspectContract.TAB_CHECK_SELF;
                break;
            case 2:
                checkName = BaseInspectContract.TAB_SUPERVISE_CHECK;
                break;
            case 3:
                checkName = BaseInspectContract.TAB_TERRITORY_SUPERVISE_CHECK;
                break;
            case 4:
                checkName = BaseInspectContract.TAB_GRID_SUPERVISE_CHECK;
                break;
            default:
                break;
        }
        return checkName;
    }
    /**
     * 获取状态检查类型
     * 1常规检查；2专项检查；3验收检查）
     *
     * @return
     */
    private String getSmallCheckName(int type) {

        String checkName =null;
        switch (type) {
            case 1:
                checkName = "常规检查";
                break;
            case 2:
                checkName = "专项检查";
                break;
            case 3:
                checkName = "验收检查";
                break;
            default:
                break;
        }
        return checkName;
    }
}