package com.juntai.upcodesafe.mine.addInformation;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.base.BaseActivity;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.PickerManager;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.BaseAppActivity;
import com.juntai.upcodesafe.base.HeadCropActivity;
import com.juntai.upcodesafe.bean.IdNameBean;
import com.juntai.upcodesafe.bean.UnitsBean;
import com.juntai.upcodesafe.mine.MyCenterContract;
import com.juntai.upcodesafe.mine.MyCenterPresent;
import com.juntai.upcodesafe.mine.addInformation.addUnit.ManualAddUnitActivity;
import com.juntai.upcodesafe.utils.UserInfoManager;

import java.io.File;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @aouther tobato
 * @description 描述  补充资料
 * @date 2021-10-10 15:16
 */
public class AddInformationActivity extends BaseAppActivity<MyCenterPresent> implements MyCenterContract.ICenterView, View.OnClickListener {

    /**
     * 请输入你的姓名
     */
    private EditText mNickNameEt;
    private ImageView mFormHeadPicIv;
    /**
     * 手机号:fasdfasd
     */
    private TextView mPhoneTv;
    /**
     * 监管单位
     */
    private RadioButton mInspectUnitRb;
    /**
     * 属地监管
     */
    private RadioButton mTownUnitRb;
    /**
     * 网格
     */
    private RadioButton mVillageRb;
    /**
     * 企业账号
     */
    private RadioButton mEnterpriseRb;
    private RadioGroup mAccountTypeRg;
    private SearchView mSearchSv;
    private ImageView mAddIv;
    private RecyclerView mUnitRv;
    private String TYPE_ID = "1";//搜索类型1监管单位；  2属地监管；            3网格；            4企业账号
    private int departmentId = -1;
    private int territoryId = -1;
    private int departmentSecondId = -1;


    /**
     * 已经选择\u3000\u3000***公司
     */
    private TextView mSelectedUnitTv;
    /**
     * 提交
     */
    private TextView mCommitTv;
    private BindUnitAdapter bindUnitAdapter;
    private TextView mNextDepartmentValueTv;
    private ConstraintLayout mNextDepartmentCl;
    private String path;

    @Override
    protected MyCenterPresent createPresenter() {
        return new MyCenterPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_add_information;
    }

    @Override
    public void initView() {
        setTitleName("补充信息");
        mNickNameEt = (EditText) findViewById(R.id.nick_name_et);
        mFormHeadPicIv = (ImageView) findViewById(R.id.form_head_pic_iv);
        mFormHeadPicIv.setOnClickListener(this);
        mPhoneTv = (TextView) findViewById(R.id.phone_tv);
        mPhoneTv.append(UserInfoManager.getUserAccount());
        mInspectUnitRb = (RadioButton) findViewById(R.id.inspect_unit_rb);
        mTownUnitRb = (RadioButton) findViewById(R.id.town_unit_rb);
        mVillageRb = (RadioButton) findViewById(R.id.village_rb);
        mEnterpriseRb = (RadioButton) findViewById(R.id.enterprise_rb);
        mAccountTypeRg = (RadioGroup) findViewById(R.id.account_type_rg);
        mSearchSv = (SearchView) findViewById(R.id.search_sv);
        mAddIv = (ImageView) findViewById(R.id.add_iv);
        mAddIv.setOnClickListener(this);
        mUnitRv = (RecyclerView) findViewById(R.id.unit_rv);
        mSelectedUnitTv = (TextView) findViewById(R.id.selected_unit_tv);
        mCommitTv = (TextView) findViewById(R.id.commit_tv);
        mCommitTv.setOnClickListener(this);
        mSearchSv.setOnClickListener(this);
        mNextDepartmentValueTv = (TextView) findViewById(R.id.next_department_value_tv);
        mNextDepartmentValueTv.setOnClickListener(this);
        mNextDepartmentCl = (ConstraintLayout) findViewById(R.id.next_department_cl);
    }

    @Override
    public void initData() {
        bindUnitAdapter = new BindUnitAdapter(R.layout.check_item);
        bindUnitAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                UnitsBean.DataBean dataBean = (UnitsBean.DataBean) adapter.getData().get(position);
                mSelectedUnitTv.setVisibility(View.VISIBLE);
                mSelectedUnitTv.append(dataBean.getName());
                departmentId = dataBean.getId();
                territoryId = dataBean.getTerritoryId();
                if (mInspectUnitRb.isChecked()) {
                    departmentSecondId = -1;
                    mNextDepartmentCl.setVisibility(View.VISIBLE);
                } else {
                    mNextDepartmentCl.setVisibility(View.GONE);
                }
                bindUnitAdapter.setNewData(null);
            }
        });
        initRecyclerviewNoScroll(mUnitRv, bindUnitAdapter, LinearLayoutManager.VERTICAL);
        mAccountTypeRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setViewsGone(mAddIv, mSelectedUnitTv, mNextDepartmentCl);
                switch (checkedId) {
                    case R.id.inspect_unit_rb:
                        TYPE_ID = "1";
                        break;
                    case R.id.town_unit_rb:
                        TYPE_ID = "2";
                        break;
                    case R.id.village_rb:
                        TYPE_ID = "3";
                        break;
                    case R.id.enterprise_rb:
                        TYPE_ID = "4";
                        setViewsVisible(mAddIv);
                        break;
                    default:
                        break;
                }
                startSearch();
            }
        });
        mSearchSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (TextUtils.isEmpty(s)) {
                    ToastUtils.toast(mContext, "请输入要搜索的内容");
                    return true;
                }
                startSearch();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    /**
     * 开始搜索
     */
    private void startSearch() {
        if (!TextUtils.isEmpty(mSearchSv.getQuery().toString().trim())) {
            mPresenter.searchAccountNature(getBaseBuilder().add("keyword", mSearchSv.getQuery().toString().trim()).add("typeId", TYPE_ID).build(), AppHttpPath.SEARCH_ACCOUNT_NATURE);
        }
    }


    @Override
    public void onSuccess(String tag, Object o) {
        getViewFocus(mPhoneTv);
        switch (tag) {
            case AppHttpPath.SEARCH_ACCOUNT_NATURE:
                UnitsBean unitsBean = (UnitsBean) o;
                if (unitsBean != null) {
                    List<UnitsBean.DataBean> arrays = unitsBean.getData();
                    bindUnitAdapter.setNewData(arrays);
                    if (arrays.isEmpty()) {
                        ToastUtils.toast(mContext, "没有查到相关信息");
                    }
                }
                break;
            case AppHttpPath.GET_NEXT_DEPARTMENT:
                IdNameBean idNameBean = (IdNameBean) o;
                if (idNameBean != null) {
                    List<IdNameBean.DataBean> dataBeans = idNameBean.getData();
                    if (!dataBeans.isEmpty()) {
                        PickerManager.getInstance().showOptionPicker(mContext, dataBeans, new PickerManager.OnOptionPickerSelectedListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                IdNameBean.DataBean dataBean = dataBeans.get(options1);
                                departmentSecondId = dataBean.getId();
                                mNextDepartmentValueTv.setText(dataBean.getName());
                            }
                        });
                    }
                }
                break;

            case AppHttpPath.ADD_INFO:
                ToastUtils.toast(mContext, "绑定成功 等待审核");
                setResult(BaseActivity.BASE_REQUEST_RESULT);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String tag, Object o) {
        super.onError(tag, o);
        getViewFocus(mPhoneTv);
    }

    @Override
    protected void selectedPicsAndEmpressed(List<String> icons) {
        if (icons.size() > 0) {
            String path = icons.get(0);
            //跳转到裁剪头像的界面
            startActivityForResult(new Intent(this, HeadCropActivity.class).putExtra(HeadCropActivity.HEAD_PIC,
                    path), BASE_REQUEST_RESULT);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.form_head_pic_iv:
                choseImage(0, AddInformationActivity.this, 1);
                break;
            case R.id.add_iv:
                startActivity(new Intent(mContext, ManualAddUnitActivity.class));
                break;
            case R.id.search_sv:
                if (TextUtils.isEmpty(mSearchSv.getQuery().toString().trim())) {
                    ToastUtils.toast(mContext, "请输入要搜索的内容");
                    return;
                }
                startSearch();
                break;
            case R.id.commit_tv:
                if (TextUtils.isEmpty(getTextViewValue(mNickNameEt))) {
                    ToastUtils.toast(mContext, "请输入你的姓名");
                    return;
                }
                if (TextUtils.isEmpty(path)) {
                    ToastUtils.toast(mContext, "请选择头像图片");
                    return;
                }
                if (!mSelectedUnitTv.isShown() || TextUtils.isEmpty(getTextViewValue(mSelectedUnitTv))) {
                    ToastUtils.toast(mContext, "请先搜索绑定，绑定后才能提交");
                    return;
                }
                MultipartBody.Builder builder = getPublishMultipartBody().addFormDataPart("id", String.valueOf(UserInfoManager.getUserId()))
                        .addFormDataPart("nickname", getTextViewValue(mNickNameEt))
                        .addFormDataPart("typeId", TYPE_ID).addFormDataPart("headPicture", "headPicture",
                                RequestBody.create(MediaType.parse("file"),
                                        new File(path)));
                ;

                switch (TYPE_ID) {
                    case "1":

                        if (!mNextDepartmentCl.isShown() || TextUtils.isEmpty(getTextViewValue(mNextDepartmentValueTv))) {
                            ToastUtils.toast(mContext, "请选择下级部门");
                            return;
                        }

                        mPresenter.addInfo(builder.addFormDataPart("departmentId", String.valueOf(departmentId))
                                .addFormDataPart("departmentSecondId", String.valueOf(departmentSecondId)).build(), AppHttpPath.ADD_INFO);
                        break;
                    case "2":
                        mPresenter.addInfo(builder.addFormDataPart("territoryId", String.valueOf(departmentId)).build(), AppHttpPath.ADD_INFO);
                        break;
                    case "3":
                        mPresenter.addInfo(builder
                                .addFormDataPart("territoryId",String.valueOf(territoryId))
                                .addFormDataPart("gridId", String.valueOf(departmentId)).build(), AppHttpPath.ADD_INFO);
                        break;
                    case "4":
                        mPresenter.addInfo(builder.addFormDataPart("unitId", String.valueOf(departmentId)).build(), AppHttpPath.ADD_INFO);
                        break;
                    default:
                        break;
                }


                break;
            case R.id.next_department_value_tv:
                mPresenter.getNextDepartment(getBaseBuilder().add("id", String.valueOf(departmentId)).build(), AppHttpPath.GET_NEXT_DEPARTMENT);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == BASE_REQUEST_RESULT) {
            if (data != null) {
                path = data.getStringExtra(HeadCropActivity.CROPED_HEAD_PIC);
                ImageLoadUtil.loadImageNoCache(mContext, path, mFormHeadPicIv);
            }

        }

    }

}
