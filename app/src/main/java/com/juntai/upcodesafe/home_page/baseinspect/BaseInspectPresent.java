package com.juntai.upcodesafe.home_page.baseinspect;

import android.text.TextUtils;

import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.AppNetModule;
import com.juntai.upcodesafe.base.BaseAppPresent;
import com.juntai.upcodesafe.bean.ImportantTagBean;
import com.juntai.upcodesafe.bean.ItemFragmentBean;
import com.juntai.upcodesafe.bean.LocationBean;
import com.juntai.upcodesafe.bean.MultipleItem;
import com.juntai.upcodesafe.bean.TextKeyValueBean;
import com.juntai.upcodesafe.bean.TownListBean;
import com.juntai.upcodesafe.bean.UnitDetailBean;
import com.juntai.upcodesafe.utils.UrlFormatUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/18 16:44
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/18 16:44
 */
public class BaseInspectPresent extends BaseAppPresent<IModel, BaseInspectContract.IInspectView> implements BaseInspectContract.IInspectPresent {
    @Override
    protected IModel createModel() {
        return null;
    }

    /**
     * 治安巡检点更多信息
     * @return
     */
    public List<MultipleItem> getMoreInfoDetail() {
        List<MultipleItem> arrays = new ArrayList<>();
        arrays.add(new MultipleItem(MultipleItem.ITEM_NORMAL_RECYCLEVIEW,
                getData()));
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.REMARK, "", false, 1);
        arrays.add(new MultipleItem(MultipleItem.ITEM_LOCATION, "地址"));
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, "现场图片"));
        arrays.add(new MultipleItem(MultipleItem.ITEM_FRAGMENT,""));
        return arrays;
    }

    /**
     *
     * @return
     */
    public List<MultipleItem> getEditSecurityInspectSiteInfo() {
        List<MultipleItem> arrays = new ArrayList<>();
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_SITE, "", false, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_ADDR, "", false, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_RESPONSIBLE, "", false, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_TEL, "", false, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_SPARE_PERSON, "", false, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_SPARE_PERSON_TEL, "", false, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.REMARK, "", false, 1);
        arrays.add(new MultipleItem(MultipleItem.ITEM_LOCATION, "地址"));
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, "现场图片"));
        arrays.add(new MultipleItem(MultipleItem.ITEM_FRAGMENT,""));
        return arrays;
    }

    private List<TextKeyValueBean> getData() {
        List<TextKeyValueBean> arrays = new ArrayList<>();
        arrays.add(new TextKeyValueBean("巡检点:", "暂无"));
        arrays.add(new TextKeyValueBean("巡检地址:", "暂无"));
        arrays.add(new TextKeyValueBean("安全责任人:", "暂无"));
        arrays.add(new TextKeyValueBean("联系电话:", "暂无"));
        arrays.add(new TextKeyValueBean("备用联系人:", "暂无"));
        arrays.add(new TextKeyValueBean("联系电话:", "暂无"));
        return arrays;
    }
    /**
     * 添加 单位详情
     *
     * @return
     */
    public List<MultipleItem> getUnitInfoData(UnitDetailBean.DataBean bean, boolean isDetail) {
        List<MultipleItem> arrays = new ArrayList<>();
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_UNIT_NAME, bean == null ? "" :
                        bean.getName(),
                true,
                0);

        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_UNIT_UCC, bean == null ? "" :
                        bean.getUnifiedCreditCode(), true
                , 0);
        initTextType(arrays, MultipleItem.ITEM_SELECT, BaseInspectContract.INSPECTION_UNIT_AREA, bean == null ? "" :
                        bean.getTerritoryName()
                , true, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_UNIT_ADDR_DETAIL, bean == null ? "" :
                        bean.getAddress()
                , true, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_UNIT_LEGAL_PERSON, bean == null ?
                "" :
                bean.getLegal(), true, 0);

        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_LEGAL_TEL, bean == null ? "" :
                bean.getLegalPhone(), true, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_RESPONSIBLE, bean == null ? "" :
                bean.getPersonLiable(), true, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_RESPONSIBLE_TEL, bean == null ? "" :
                bean.getLiablePhone(), true, 0);

        arrays.add(new MultipleItem(MultipleItem.ITEM_LOCATION, new LocationBean(bean == null ? null :
                bean.getGpsAddress()
                , bean == null ? null : bean.getLatitude(), bean == null ? null : bean.getLongitude())));
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, "上传单位图片"));
        List<String> fragmentPics = new ArrayList<>();
        if (bean != null) {
            addFragmentPics(bean.getCoverPicture(), fragmentPics);
            addFragmentPics(bean.getPhotoTwo(), fragmentPics);
            addFragmentPics(bean.getPhotoThree(), fragmentPics);
        }
        arrays.add(new MultipleItem(MultipleItem.ITEM_FRAGMENT, new ItemFragmentBean(3, isDetail ?
                fragmentPics.size() : 3,
                3, true,
                fragmentPics)));
        return arrays;
    }
    private void addFragmentPics(String picPath, List<String> fragmentPics) {
        if (!TextUtils.isEmpty(picPath)) {
            if (picPath.contains(BaseInspectionActivity.SDCARD_TAG)) {
                fragmentPics.add(picPath);
            } else {
                if (picPath.contains(AppHttpPath.BASE_IMAGE)) {
                    fragmentPics.add(picPath);
                } else {
                    fragmentPics.add(UrlFormatUtil.getImageOriginalUrl(picPath));
                }

            }

        }
    }

    /**
     * initTextType
     *
     * @param arrays
     * @param typeName
     * @param editHeightType 0代表高度固定 1代表不固定
     */
    private void initTextType(List<MultipleItem> arrays, int layoutType, String typeName, String value,
                              boolean isImportant, int editHeightType) {
        switch (layoutType) {
            case MultipleItem.ITEM_SELECT:
                arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_SMALL, new ImportantTagBean
                        (typeName, isImportant)));
                arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT,
                        new TextKeyValueBean(typeName, value, String.format("%s%s", "请选择",
                                typeName), 0, isImportant)));
                break;
            case MultipleItem.ITEM_EDIT:
                arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_SMALL, new ImportantTagBean(typeName,
                        isImportant)));
                arrays.add(new MultipleItem(MultipleItem.ITEM_EDIT,
                        new TextKeyValueBean(typeName, value,
                                String.format("%s%s", "请输入", typeName), editHeightType, isImportant)));

                break;
            case MultipleItem.ITEM_EDIT2:
                arrays.add(new MultipleItem(MultipleItem.ITEM_EDIT2,
                        new TextKeyValueBean(typeName, value,
                                String.format("%s%s", "请输入", typeName), editHeightType, isImportant)));
                break;
            default:
                break;
        }

    }

    public void getTownList(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getTownList(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<TownListBean>(getView()) {
                    @Override
                    public void onSuccess(TownListBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }
    public void manualAddUnit(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .manualAddUnit(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }





}
