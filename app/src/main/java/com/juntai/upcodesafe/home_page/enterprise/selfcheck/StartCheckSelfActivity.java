package com.juntai.upcodesafe.home_page.enterprise.selfcheck;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.UnitDetailBean;
import com.juntai.upcodesafe.home_page.baseinspect.BaseCommitFootViewActivity;
import com.juntai.upcodesafe.home_page.baseinspect.TextKeyValueAdapter;

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
        adapter.setHeaderView(getHeadView());
        adapter.setNewData(mPresenter.getCheckData());
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

    }

    @Override
    protected void saveDraft() {

    }

    @Override
    protected String getTitleName() {
        return "开始自查";
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

}
