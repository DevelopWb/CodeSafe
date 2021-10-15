package com.juntai.upcodesafe.home_page.baseinspect;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.GsonTools;
import com.juntai.disabled.basecomponent.utils.PickerManager;
import com.juntai.disabled.basecomponent.utils.RuleTools;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.bdmap.act.LocateSelectionActivity;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.BaseAppActivity;
import com.juntai.upcodesafe.base.HeadCropActivity;
import com.juntai.upcodesafe.base.selectPics.SelectPhotosFragment;
import com.juntai.upcodesafe.bean.BaseAdapterDataBean;
import com.juntai.upcodesafe.bean.CheckDesJsonBena;
import com.juntai.upcodesafe.bean.CheckDetailBean;
import com.juntai.upcodesafe.bean.DesAndPicBean;
import com.juntai.upcodesafe.bean.IdNameBean;
import com.juntai.upcodesafe.bean.ItemFragmentBean;
import com.juntai.upcodesafe.bean.LocationBean;
import com.juntai.upcodesafe.bean.MultipleItem;
import com.juntai.upcodesafe.bean.PicRecycleBean;
import com.juntai.upcodesafe.bean.TextKeyValueBean;
import com.juntai.upcodesafe.bean.TownListBean;
import com.juntai.upcodesafe.bean.UnitDetailBean;
import com.juntai.upcodesafe.utils.StringTools;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhihu.matisse.Matisse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @Author: tobato
 * @Description: 作用描述  巡检的基类
 * @CreateDate: 2021/4/22 11:08
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/22 11:08
 */
public abstract class BaseInspectionActivity extends BaseAppActivity<BaseInspectPresent> implements BaseInspectContract.IInspectView, View.OnClickListener, SelectPhotosFragment.OnPhotoItemClick {
    public boolean idDetail = false;

    private int mParentsPosition;
    private PicRecycleBean mPicRecycleBean;
    private HorPicsAdapter mHorPicsAdapter;
    protected BaseInspectionAdapter adapter;
    private RecyclerView mRecyclerview;
    private SmartRefreshLayout mSmartrefreshlayout;
    public static String PARCELABLE_KEY = "parcelable";
    public static String SDCARD_TAG = "/storage/emulated";
    int mMaxCount = 3;
    private TextKeyValueBean selectBean;
    private TextView mSelectTv;
    private int currentPosition;
    protected int fragmentPosition = 0;
    public TextView mCommitTv;

    protected abstract String getTitleName();


    private int townId;
    private int countyId;

    @Override
    protected BaseInspectPresent createPresenter() {
        return new BaseInspectPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.recycleview_layout;
    }

    @Override
    public void initView() {
        setTitleName(getTitleName());
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        mSmartrefreshlayout = (SmartRefreshLayout) findViewById(R.id.smartrefreshlayout);
        mSmartrefreshlayout.setEnableLoadMore(false);
        mSmartrefreshlayout.setEnableRefresh(false);
        adapter = new BaseInspectionAdapter(null, idDetail, getSupportFragmentManager());
        initRecyclerview(mRecyclerview, adapter, LinearLayoutManager.VERTICAL);
        if (getFootView() != null) {
            adapter.setFooterView(getFootView());
        }
        setAdapterClick();

    }

    protected View getFootView() {
        View view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.footview_commit, null);
        mCommitTv = view.findViewById(R.id.commit_form_tv);
        mCommitTv.setText("提交");
        mCommitTv.setOnClickListener(this);
        return view;
    }

    private void setAdapterClick() {


        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                currentPosition = position;
                MultipleItem multipleItem = (MultipleItem) adapter.getData().get(position);
                switch (view.getId()) {
//                    case R.id.form_head_pic_iv:
//                        HeadPicBean headPicBean = (HeadPicBean) multipleItem.getObject();
//                        String headPicPath = headPicBean.getPicPath();
//                        if (TextUtils.isEmpty(headPicPath)) {
//                            choseImage(0, BaseInspectionActivity.this, 1);
//                        } else {
//                            if (headPicPath.contains("/key_personnel/keyPersonnel.png")||headPicPath.contains(SDCARD_TAG)) {
//                                choseImage(0, BaseInspectionActivity.this, 1);
//                            } else {
//                                ArrayList<String> photos = new ArrayList<>();
//                                photos.add(UrlFormatUtil.getImageOriginalUrl(headPicPath));
//                                //查看图片
//                                startActivity(new Intent(mContext, ImageZoomActivity.class)
//                                        .putExtra("paths", photos)
//                                        .putExtra("item", 0));
//                            }
//
//                        }
//
//                        break;
//                    case R.id.sign_ll:
//                        itemSignBean = (ItemSignBean) multipleItem.getObject();
//                        //签名
//                        mSignIv = (ImageView) view.findViewById(R.id.sign_name_iv);
//                        showSignatureView();
//                        break;

                    case R.id.select_value_tv:
                        mSelectTv = (TextView) adapter.getViewByPosition(mRecyclerview, position,
                                R.id.select_value_tv);
                        if (mSelectTv == null) {
                            mSelectTv = (TextView) adapter.getViewByPosition(mRecyclerview, position + 1,
                                    R.id.select_value_tv);
                        }
                        selectBean = (TextKeyValueBean) multipleItem.getObject();
                        switch (selectBean.getKey()) {
                            case BaseInspectContract.INSPECTION_UNIT_AREA:
                                mPresenter.getTownList(getBaseBuilder().build(), AppHttpPath.GET_TOWN_LIST);
                                break;
//                            case BaseInspectContract.INSPECTION_VISIT_TIMES:
//                                List<String> nums = getNums();
//                                List<List<String>> timeUnits = getTimeUnits();
//                                PickerManager.getInstance().showOptionPicker(mContext, nums, timeUnits,
//                                        new PickerManager.OnOptionPickerSelectedListener() {
//                                            @Override
//                                            public void onOptionsSelect(int options1, int option2, int options3,
//                                                                        View v) {
//                                                String day = String.format("%s%s", nums.get(options1),
//                                                        timeUnits.get(options1).get(option2));
//                                                selectBean.setValue(String.valueOf(getVistTime(nums.get(options1),
//                                                        timeUnits.get(options1).get(option2))));
//                                                mSelectTv.setText(day);
//                                            }
//                                        });
//                                break;

                            default:
                                break;
                        }
                        break;

                    case R.id.location_ll:
                        //跳转到选择位置类
                        startActivityForResult(new Intent(mContext, LocateSelectionActivity.class),
                                LocateSelectionActivity.SELECT_ADDR);
                        break;

                    case R.id.add_more_item_tv:
                        //添加一组条目
                        fragmentPosition++;
                        adapter.addData(adapter.getData().size() - 1, mPresenter.getCheckData(fragmentPosition));
                        break;
                    default:
                        break;
                }

            }
        });

        adapter.setOnPicRecyclerviewCallBack(new BaseInspectionAdapter.OnPicRecyclerviewCallBack() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position, PicRecycleBean picRecycleBean, int parentsPosition) {
                mPicRecycleBean = picRecycleBean;
                mParentsPosition = parentsPosition;
                mHorPicsAdapter = (HorPicsAdapter) adapter;
                List<String> arrays = reSortIconList();
                String icon_path = arrays.get(position);
                switch (view.getId()) {
                    case R.id.select_pic_icon_iv:
                        if ("-1".equals(icon_path)) {
                            int count = mMaxCount - (adapter.getData().size() - 1);
                            choseImage(0, BaseInspectionActivity.this, count);
                        } else {
                            if (icon_path.contains(".mp4")) {
//                                //视频路径
//                                if (onPhotoItemClick != null) {
//                                    onPhotoItemClick.onVedioPicClick(adapter, position);
//                                }
//                            } else {
//                                //图片路径
//                                if (onPhotoItemClick != null) {
//                                    onPhotoItemClick.onPicClick(adapter, position);
//                                }
                            }
                        }
                        break;
                    case R.id.delete_pushed_news_iv:
                        arrays.remove(position);
                        if (arrays.size() < mMaxCount) {
                            if (!arrays.contains("-1")) {
                                arrays.add("-1");
                            }
                        }
                        adapter.setNewData(arrays);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void selectedPicsAndEmpressed(List<String> icons) {
        if (icons.size() > 0) {
            mHorPicsAdapter.addData(icons);
            List<String> arrays = reSortIconList();
            mPicRecycleBean.setPics(arrays);
            adapter.notifyItemChanged(mParentsPosition);
        }
    }

    /**
     * 对icons集合处理
     *
     * @return
     */
    private List<String> reSortIconList() {
        List<String> icons_new = new ArrayList<>();
        for (String icon : mHorPicsAdapter.getData()) {
            if (!"-1".equals(icon)) {
                icons_new.add(icon);
            }
        }
        if (mHorPicsAdapter.getData().size() <= mMaxCount) {
            icons_new.add("-1");
        }
        return icons_new;
    }

    /**
     * 获取adapter中的数据
     * skipFilter  跳过过滤
     *
     * @return
     */
    protected BaseAdapterDataBean getBaseAdapterData(boolean skipFilter) {
        List<CheckDesJsonBena> arr = new ArrayList<>();
        BaseAdapterDataBean bean = new BaseAdapterDataBean();
        UnitDetailBean.DataBean unitDataBean = new UnitDetailBean.DataBean();
        MultipartBody.Builder builder = mPresenter.getPublishMultipartBody();
        List<MultipleItem> arrays = adapter.getData();
        for (MultipleItem array : arrays) {
            switch (array.getItemType()) {
//                case MultipleItem.ITEM_SIGN:
//                    //签名
//                    ItemSignBean signBean = (ItemSignBean) array.getObject();
//                    if (!StringTools.isStringValueOk(signBean.getSignPicPath())) {
//                        ToastUtils.toast(mContext, "请签名");
//                        return null;
//                    }
//                    builder.addFormDataPart("pictureSign", "pictureSign",
//                            RequestBody.create(MediaType.parse(
//                                    "file"), new File(getSignPath(FileCacheUtils.SIGN_PIC_NAME))));
//                    break;
//                case MultipleItem.ITEM_HEAD_PIC:
//                    HeadPicBean headPicBean = (HeadPicBean) array.getObject();
//                    String headPicPath = headPicBean.getPicPath();
//
//                    if (!skipFilter) {
//                        if (TextUtils.isEmpty(headPicPath)) {
//                            ToastUtils.toast(mContext, "请选择头像照片");
//                            return null;
//                        }
//                    }
//
//                    importantorBean.setPersonnelPhoto(headPicPath);
//                    workerBean.setPersonnelPhoto(headPicPath);
//
//                    if (headPicPath.contains(SDCARD_TAG)) {
//                        builder.addFormDataPart("personnelPicture", "personnelPicture",
//                                RequestBody.create(MediaType.parse("file"),
//                                        new File(headPicPath)));
//                    } else {
//                        if (headPicPath.contains(AppHttpPath.BASE_IMAGE)) {
//                            builder.addFormDataPart("personnelPhoto",
//                                    headPicPath.substring(AppHttpPath.BASE_IMAGE.length(),
//                                            headPicPath.length()));
//                        }
//
//                    }
//                    break;
                case MultipleItem.ITEM_EDIT:
                    TextKeyValueBean textValueEditBean = (TextKeyValueBean) array
                            .getObject();
                    String value = textValueEditBean.getValue();
                    if (!skipFilter) {
                        if (textValueEditBean.isImportant() && TextUtils.isEmpty(textValueEditBean
                                .getValue())) {
                            String key = textValueEditBean.getKey();
                            //                        if (key.contains(mPresenter.FAMILY_TAG)) {
                            //                            key = "监护人" + key.substring(1, key.length());
                            //                        } else if (key.contains(mPresenter.PERSIONAL_TAG)) {
                            //                            key = "残疾人" + key.substring(1, key.length());
                            //                        }
                            ToastUtils.toast(mContext, "请输入" + key);
                            return null;
                        }
                    }

                    String formKey = null;
                    switch (textValueEditBean.getKey()) {
                        case BaseInspectContract.INSPECTION_TEL:
                            //联系电话
                            if (!skipFilter) {
                                if (textValueEditBean.isImportant() && !RuleTools.isMobileNO(value)) {
                                    ToastUtils.toast(mContext, "联系电话格式不正确");
                                    return null;
                                }
                            }
                            formKey = "phone";
                            break;
                        case BaseInspectContract.INSPECTION_UNIT_NAME:
                            //单位名称
                            formKey = "name";
                            unitDataBean.setName(value);
                            break;
                        case BaseInspectContract.INSPECTION_UNIT_ADDR_DETAIL:
                            //详细地址
                            formKey = "address";
                            unitDataBean.setAddress(value);
                            break;
                        case BaseInspectContract.INSPECTION_UNIT_UCC:
                            //社会信用代码
                            formKey = "unifiedCreditCode";
                            unitDataBean.setUnifiedCreditCode(value);
                            break;
                        case BaseInspectContract.INSPECTION_UNIT_LEGAL_PERSON:
                            //单位法人
                            formKey = "legal";
                            unitDataBean.setLegal(value);
                            break;
                        case BaseInspectContract.INSPECTION_LEGAL_TEL:
                            //法人手机号
                            if (!skipFilter) {
                                if (textValueEditBean.isImportant() && !RuleTools.isMobileNO(value)) {
                                    ToastUtils.toast(mContext, "法人手机号格式不正确");
                                    return null;
                                }
                            }
                            formKey = "legalPhone";
                            unitDataBean.setLegalPhone(value);
                            break;
                        case BaseInspectContract.INSPECTION_RESPONSIBLE:
                            //安全责任人
                            formKey = "personLiable";
                            unitDataBean.setPersonLiable(value);
                            break;
                        case BaseInspectContract.INSPECTION_RESPONSIBLE_TEL:
                            //安全责任人电话
                            if (!skipFilter) {
                                if (textValueEditBean.isImportant() && !RuleTools.isMobileNO(value)) {
                                    ToastUtils.toast(mContext, "安全责任人电话格式不正确");
                                    return null;
                                }
                            }
                            formKey = "liablePhone";
                            unitDataBean.setLiablePhone(value);
                            break;
                        case BaseInspectContract.INSPECTION_SPARE_PERSON:
                            //备用联系人
                            formKey = "sparePerson";
                            break;
                        case BaseInspectContract.INSPECTION_SPARE_PERSON_TEL:
                            //备用联系人电话
                            if (!skipFilter) {
                                if (textValueEditBean.isImportant() && !RuleTools.isMobileNO(value)) {
                                    ToastUtils.toast(mContext, "备用联系人电话格式不正确");
                                    return null;
                                }
                            }
                            formKey = "sparePhone";
                            break;
                        case BaseInspectContract.REMARK:
                            //备注
                            formKey = "remarks";
                            break;
                        default:
                            break;
                    }
                    if (StringTools.isStringValueOk(value) && formKey != null) {
                        builder.addFormDataPart(formKey, value);
                    }

                    break;
                case MultipleItem.ITEM_SELECT:
                    TextKeyValueBean textValueSelectBean = (TextKeyValueBean) array.getObject();
                    String selectBeanValue = textValueSelectBean.getValue();
                    if (!skipFilter) {
                        if (textValueSelectBean.isImportant() && !StringTools.isStringValueOk(selectBeanValue)) {
                            ToastUtils.toast(mContext, "请选择" + textValueSelectBean.getKey());
                            return null;
                        }
                    }

                    switch (textValueSelectBean.getKey()) {
                        case BaseInspectContract.INSPECTION_UNIT_AREA:
                            if (textValueSelectBean.getIds().contains(",")) {
                                String[] ids = textValueSelectBean.getIds().split(",");
                                builder.addFormDataPart("territoryOneId", ids[0]);
                                builder.addFormDataPart("territoryTwoId", ids[1]);
                            }
                            unitDataBean.setTerritoryName(selectBeanValue);
                            if (!TextUtils.isEmpty(textValueSelectBean.getIds())) {
                                unitDataBean.setIds(textValueSelectBean.getIds());
                            }

                            break;
                        default:
                            break;
                    }
                    break;
                case MultipleItem.ITEM_LOCATION:
                    LocationBean locationBean = (LocationBean) array.getObject();
                    builder.addFormDataPart("gpsAddress", locationBean.getAddress());
                    builder.addFormDataPart("longitude", locationBean.getLongitude());
                    builder.addFormDataPart("latitude", locationBean.getLatitude());
                    unitDataBean.setGpsAddress(locationBean.getAddress());
                    unitDataBean.setLatitude(locationBean.getLatitude());
                    unitDataBean.setLongitude(locationBean.getLongitude());
                    break;
                case MultipleItem.ITEM_DES_PIC:
                    DesAndPicBean desAndPicBean = (DesAndPicBean) array.getObject();
                    PicRecycleBean picRecycleBean = desAndPicBean.getPicRecycleBean();
                    CheckDesJsonBena checkDesJsonBena = new CheckDesJsonBena();
                    if (!TextUtils.isEmpty(desAndPicBean.getTextKeyValueBean().getValue())) {
                        checkDesJsonBena.setConcreteProblem(desAndPicBean.getTextKeyValueBean().getValue());
                    }
                    if (!picRecycleBean.getPics().isEmpty()) {
                        for (int i = 0; i < picRecycleBean.getPics().size(); i++) {
                            String pic = picRecycleBean.getPics().get(i);
                            if (!"-1".equals(pic)) {
                                switch (i) {
                                    case 0:
                                        checkDesJsonBena.setPhotoOne(pic);
                                        break;
                                    case 1:
                                        checkDesJsonBena.setPhotoTwo(pic);
                                        break;
                                    case 2:
                                        checkDesJsonBena.setPhotoThree(pic);
                                        break;
                                    default:
                                        break;
                                }
                            }

                        }
                    }
                    // TODO: 2021-10-15 这个应该能解决间隔填写内容的问题
                    if (TextUtils.isEmpty(desAndPicBean.getTextKeyValueBean().getValue()) && picRecycleBean.getPics().isEmpty()) {

                    } else {
                        arr.add(checkDesJsonBena);
                    }


                    break;
                case MultipleItem.ITEM_FRAGMENT:
                    ItemFragmentBean fragmentBean = (ItemFragmentBean) array.getObject();
                    List<String> photos = fragmentBean.getFragmentPics();
                    if (!skipFilter) {
                        if (photos.isEmpty()) {
                            ToastUtils.toast(mContext, "请选择图片");
                            return null;
                        }
                        if (photos.size() < fragmentBean.getMinCount()) {
                            ToastUtils.toast(mContext, "最少需要" + fragmentBean.getMinCount() + "张照片");
                            return null;
                        }
                    }

                    for (int i = 0; i < photos.size(); i++) {
                        String picPah = photos.get(i);
                        switch (i) {
                            case 0:

                                if (picPah.contains(SDCARD_TAG)) {
                                    builder.addFormDataPart("pictureOne", "pictureOne",
                                            RequestBody.create(MediaType.parse("file"),
                                                    new File(picPah)));
                                } else {
                                    builder.addFormDataPart("pictureOne",
                                            picPah.substring(AppHttpPath.BASE_IMAGE.length(),
                                                    picPah.length()));
                                }
                                unitDataBean.setCoverPicture(picPah);
                                break;
                            case 1:
                                if (picPah.contains(SDCARD_TAG)) {
                                    builder.addFormDataPart("pictureTwo", "pictureTwo",
                                            RequestBody.create(MediaType.parse("file"),
                                                    new File(picPah)));
                                } else {
                                    builder.addFormDataPart("photoTwo",
                                            picPah.substring(AppHttpPath.BASE_IMAGE.length(),
                                                    picPah.length()));
                                }
                                unitDataBean.setPhotoTwo(picPah);
                                break;
                            case 2:
                                if (picPah.contains(SDCARD_TAG)) {
                                    builder.addFormDataPart("pictureThree", "pictureThree",
                                            RequestBody.create(MediaType.parse("file"),
                                                    new File(picPah)));
                                } else {
                                    builder.addFormDataPart("photoThree",
                                            picPah.substring(AppHttpPath.BASE_IMAGE.length(),
                                                    picPah.length()));
                                }
                                unitDataBean.setPhotoThree(picPah);
                                break;
                            case 3:
                                if (picPah.contains(SDCARD_TAG)) {
                                    builder.addFormDataPart("pictureFour", "pictureFour",
                                            RequestBody.create(MediaType.parse("file"),
                                                    new File(picPah)));
                                } else {
                                    builder.addFormDataPart("photoFour",
                                            picPah.substring(AppHttpPath.BASE_IMAGE.length(),
                                                    picPah.length()));
                                }
                                break;
                            case 4:
                                if (picPah.contains(SDCARD_TAG)) {
                                    builder.addFormDataPart("pictureFive", "pictureFive",
                                            RequestBody.create(MediaType.parse("file"),
                                                    new File(picPah)));
                                } else {
                                    builder.addFormDataPart("photoFive",
                                            picPah.substring(AppHttpPath.BASE_IMAGE.length(),
                                                    picPah.length()));
                                }
                                break;
                            case 5:
                                if (picPah.contains(SDCARD_TAG)) {
                                    builder.addFormDataPart("pictureSix", "pictureSix",
                                            RequestBody.create(MediaType.parse("file"),
                                                    new File(picPah)));
                                } else {
                                    builder.addFormDataPart("photoSix",
                                            picPah.substring(AppHttpPath.BASE_IMAGE.length(),
                                                    picPah.length()));
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        if (!arr.isEmpty()) {
            builder.addFormDataPart("problemsJson", GsonTools.createGsonString(arr));
        }
        bean.setBuilder(builder);
        bean.setUnitDataBean(unitDataBean);
        bean.setConcreteProblems(GsonTools.createGsonString(arr));
        return bean;
    }


    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case AppHttpPath.GET_TOWN_LIST:
                TownListBean townListBean = (TownListBean) o;
                List<IdNameBean.DataBean> countys = new ArrayList<>();
                List<List<IdNameBean.DataBean>> towns = new ArrayList<>();
                if (townListBean != null) {
                    List<TownListBean.DataBean> dataBeans = townListBean.getData();
                    if (dataBeans != null) {
                        for (TownListBean.DataBean dataBean : dataBeans) {
                            countys.add(new IdNameBean.DataBean(dataBean.getId(), dataBean.getName()));
                            if (dataBean.getChildList() != null) {
                                List<IdNameBean.DataBean> childs = new ArrayList<>();
                                for (TownListBean.DataBean.ChildListBean childListBean : dataBean.getChildList()) {
                                    childs.add(new IdNameBean.DataBean(childListBean.getId(), childListBean.getName()));
                                    towns.add(childs);
                                }
                            }
                        }
                    }
                }

                PickerManager.getInstance().showOptionPicker(mContext, countys, towns,
                        new PickerManager.OnOptionPickerSelectedListener() {

                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3,
                                                        View v) {

                                countyId = countys.get(options1).getId();
                                townId = towns.get(options1).get(option2).getId();
                                selectBean.setIds(String.format("%s%s%s", countyId, ",", townId));

                                String area =
                                        String.format("%s%s%s", countys.get(options1).getName(), "   ", towns.get(options1).get(option2).getName());
                                selectBean.setValue(area);
                                mSelectTv.setText(area);
                            }
                        });
                break;
            default:
                break;
        }
    }

    @Override
    public void onVedioPicClick(BaseQuickAdapter adapter, int position) {

    }

    @Override
    public void onPicClick(BaseQuickAdapter adapter, int position) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commit_form_tv:
                //提交
                BaseAdapterDataBean baseAdapterDataBean = null;
                if (mCommitTv != null) {
                    if ("申请修改".equals(getTextViewValue(mCommitTv))) {
                        baseAdapterDataBean = getBaseAdapterData(true);
                    } else {
                        baseAdapterDataBean = getBaseAdapterData(false);
                    }
                } else {
                    baseAdapterDataBean = getBaseAdapterData(false);
                }

                if (baseAdapterDataBean == null) {
                    return;
                }
                commitLogic(baseAdapterDataBean.getBuilder());
                break;
            default:
                break;
        }
    }

    /**
     * 提交的逻辑
     */
    protected void commitLogic(MultipartBody.Builder builder) {
    }
}
