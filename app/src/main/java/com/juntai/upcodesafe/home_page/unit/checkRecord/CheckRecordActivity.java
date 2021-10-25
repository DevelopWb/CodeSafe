package com.juntai.upcodesafe.home_page.unit.checkRecord;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.SparseArray;

import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.upcodesafe.base.BaseTabViewPageActivity;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectContract;

/**
 * @aouther tobato
 * @description 描述 检查记录
 * @date  13:50
 */
public class CheckRecordActivity extends BaseTabViewPageActivity {

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    protected int getTabMode() {
        return TabLayout.MODE_SCROLLABLE;
    }

    @Override
    protected String gettitleName() {
        return "检查记录列表";
    }

    protected SparseArray<Fragment> getFragments() {
        SparseArray<Fragment> arrays = new SparseArray<>();

            arrays.append(0, CheckRecordFragment.newInstance(BaseInspectContract.TAB_SUPERVISE_CHECK));
            arrays.append(1, CheckRecordFragment.newInstance(BaseInspectContract.TAB_TERRITORY_SUPERVISE_CHECK));
            arrays.append(2, CheckRecordFragment.newInstance(BaseInspectContract.TAB_GRID_SUPERVISE_CHECK));
            arrays.append(3, CheckRecordFragment.newInstance(BaseInspectContract.TAB_CHECK_SELF));
            arrays.append(4, CheckRecordFragment.newInstance(BaseInspectContract.TAB_ALL));

        return arrays;
    }

    protected String[] getTabTitles() {
        return new String[]{BaseInspectContract.TAB_SUPERVISE_CHECK,
                BaseInspectContract.TAB_TERRITORY_SUPERVISE_CHECK,
                BaseInspectContract.TAB_GRID_SUPERVISE_CHECK, BaseInspectContract.TAB_CHECK_SELF, BaseInspectContract.TAB_ALL};
    }

    @Override
    public void onSuccess(String tag, Object o) {

    }
}
