package com.juntai.upcodesafe.home_page.baseinspect;

import android.text.TextUtils;

import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.utils.CalendarUtil;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.upcodesafe.AppHttpPath;
import com.juntai.upcodesafe.AppNetModule;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.BaseAppPresent;
import com.juntai.upcodesafe.bean.AddDesPicBean;
import com.juntai.upcodesafe.bean.BaseNormalRecyclerviewBean;
import com.juntai.upcodesafe.bean.BindManagerBean;
import com.juntai.upcodesafe.bean.CheckDetailBean;
import com.juntai.upcodesafe.bean.CheckRecordBean;
import com.juntai.upcodesafe.bean.DesAndPicBean;
import com.juntai.upcodesafe.bean.IdNameBean;
import com.juntai.upcodesafe.bean.ImportantTagBean;
import com.juntai.upcodesafe.bean.ItemFragmentBean;
import com.juntai.upcodesafe.bean.LocationBean;
import com.juntai.upcodesafe.bean.MultipleItem;
import com.juntai.upcodesafe.bean.PicRecycleBean;
import com.juntai.upcodesafe.bean.RectifyNoticeDeatilBean;
import com.juntai.upcodesafe.bean.RectifyNoticeListBean;
import com.juntai.upcodesafe.bean.ResponseDetailBean;
import com.juntai.upcodesafe.bean.TextKeyValueBean;
import com.juntai.upcodesafe.bean.TownListBean;
import com.juntai.upcodesafe.bean.TrainPlanListBean;
import com.juntai.upcodesafe.bean.UnitDetailBean;
import com.juntai.upcodesafe.bean.UnitsBean;
import com.juntai.upcodesafe.home_page.inspect.inspect.rectifynotice.PicOnlyAdapter;
import com.juntai.upcodesafe.utils.UrlFormatUtil;
import com.juntai.upcodesafe.utils.UserInfoManager;

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
     * 添加处罚信息
     *
     * @return
     */
    public List<MultipleItem> getPunishInfo() {
        List<MultipleItem> arrays = new ArrayList<>();
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.PUNISH_INFO, "", true, 1);
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, "上传处罚照片"));
        List<String> fragmentPics = new ArrayList<>();
        arrays.add(new MultipleItem(MultipleItem.ITEM_FRAGMENT, new ItemFragmentBean(3, 3, 1, false,
                fragmentPics)));
        return arrays;
    }

    /**
     * 添加整改通知书
     *
     * @return
     */
    public List<MultipleItem> getRectifyNoticeInfo() {
        List<MultipleItem> arrays = new ArrayList<>();
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.RECTIFY_NOTICE, "", true, 1);
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, "上传整改照片"));
        List<String> fragmentPics = new ArrayList<>();
        arrays.add(new MultipleItem(MultipleItem.ITEM_FRAGMENT, new ItemFragmentBean(3, 3, 1, false,
                fragmentPics)));
        return arrays;
    }


    /**
     * 整改通知书详情
     *
     * @param dataBean
     * @return
     */
    public List<MultipleItem> getRectifyNoticeDetailData(RectifyNoticeDeatilBean.DataBean dataBean) {
        List<MultipleItem> arrays = new ArrayList<>();
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_SMALL, new ImportantTagBean(dataBean.getContent(),
                false)));
        arrays.add(new MultipleItem(MultipleItem.ITEM_NORMAL_RECYCLEVIEW, new BaseNormalRecyclerviewBean(
                BaseInspectContract.BASE_RECYCLERVIEW_TYPE_RESPONSIBILITY_CONTENT,
                getRectifyNoticeAdapterData(dataBean), new PicOnlyAdapter(R.layout.only_pic_item))));
        return arrays;
    }

    private List<String> getRectifyNoticeAdapterData(RectifyNoticeDeatilBean.DataBean dataBean) {
        List<String> pics = new ArrayList<>();
        if (!TextUtils.isEmpty(dataBean.getPhotoOne())) {
            pics.add(dataBean.getPhotoOne());
        }
        if (!TextUtils.isEmpty(dataBean.getPhotoTwo())) {
            pics.add(dataBean.getPhotoTwo());
        }
        if (!TextUtils.isEmpty(dataBean.getPhotoThree())) {
            pics.add(dataBean.getPhotoThree());
        }
        return pics;
    }

    /**
     * 检查记录详情  没问题
     *
     * @param dataBean
     * @return
     */
    public List<MultipleItem> getCheckedDetailData(CheckDetailBean.DataBean dataBean) {
        List<MultipleItem> arrays = new ArrayList<>();
        arrays.add(new MultipleItem(MultipleItem.ITEM_NORMAL_RECYCLEVIEW, new BaseNormalRecyclerviewBean(
                BaseInspectContract.BASE_RECYCLERVIEW_TYPE_TEXT_VALUE,
                getCheckDetailData(dataBean), new TextKeyValueAdapter(R.layout.text_key_value_item))));

        List<CheckDetailBean.DataBean.ConcreteProblemsBean> problemsBeans = dataBean.getConcreteProblems();
        if (!problemsBeans.isEmpty()) {
            for (CheckDetailBean.DataBean.ConcreteProblemsBean problemsBean : problemsBeans) {
                List<String> pics = new ArrayList<>();
                if (!TextUtils.isEmpty(problemsBean.getPhotoOne())) {
                    pics.add(problemsBean.getPhotoOne());
                }
                if (!TextUtils.isEmpty(problemsBean.getPhotoTwo())) {
                    pics.add(problemsBean.getPhotoTwo());
                }
                if (!TextUtils.isEmpty(problemsBean.getPhotoThree())) {
                    pics.add(problemsBean.getPhotoThree());
                }
                DesAndPicBean desAndPicBean = new DesAndPicBean();
                desAndPicBean.setBigTitle("上传检查图片");
                desAndPicBean.setPicRecycleBean(new PicRecycleBean(pics));
                desAndPicBean.setImportantTagBean(new ImportantTagBean(BaseInspectContract.CHECK_DES, false));
                desAndPicBean.setTextKeyValueBean(new TextKeyValueBean(BaseInspectContract.CHECK_DES, problemsBean.getConcreteProblem(), "请输入" + BaseInspectContract.CHECK_DES, 1, false));
                arrays.add(new MultipleItem(MultipleItem.ITEM_DES_PIC, desAndPicBean));
            }
        }


        return arrays;
    }

    /**
     * 添加 单位详情
     *
     * @param bean
     * @param isDetail
     * @param isAddInfo 是否是在补充资料
     * @return
     */
    public List<MultipleItem> getUnitInfoData(UnitDetailBean.DataBean bean, boolean isDetail, boolean isAddInfo) {
        List<MultipleItem> arrays = new ArrayList<>();
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_UNIT_NAME, bean == null ? "" :
                        bean.getName(),
                true,
                0);

        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_UNIT_UCC, bean == null ? "" :
                        bean.getUnifiedCreditCode(), true
                , 0);
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_SMALL, new ImportantTagBean
                (BaseInspectContract.INSPECTION_UNIT_AREA, true)));
        arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT,
                new TextKeyValueBean(BaseInspectContract.INSPECTION_UNIT_AREA,
                        String.format("%s%s%s", bean == null ? "" : bean.getTerritoryOneId(), ",", bean == null ? "" : bean.getTerritoryTwoId()), String.format("%s%s", "请选择",
                        BaseInspectContract.INSPECTION_UNIT_AREA), true, bean == null ? "" : bean.getTerritoryOneName(), bean == null ? "" : bean.getTerritoryTwoName())));
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

        if (!isAddInfo) {
            if (1 == UserInfoManager.getAccountTypeId()) {
                //监管单位添加企业的时候
                arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_SMALL, new ImportantTagBean
                        (BaseInspectContract.UNIT_TYPE, true)));
                arrays.add(new MultipleItem(MultipleItem.ITEM_BUSINESS_TYPES, new TextKeyValueBean(BaseInspectContract.UNIT_TYPE
                        , bean == null ? "" : bean.getTypeName(), bean == null ? "" : String.valueOf(bean.getType()))));
                initTextSelectType(arrays, BaseInspectContract.UNIT_SIZE, bean == null ? "" : String.valueOf(bean.getScale()), bean == null ? "" :
                        getScaleName(bean.getScale()), true, "");
                initTextSelectType(arrays, BaseInspectContract.UNIT_RISK, bean == null ? "" : String.valueOf(bean.getRisk()), bean == null ? "" :
                        getRiskName(bean.getRisk()), true, "");
                arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_SMALL, new ImportantTagBean
                        ("监管单位", true)));
                arrays.add(new MultipleItem(MultipleItem.ITEM_ADD_MANAGER, new BindManagerBean(R.mipmap.direct_manager_icon,
                        BaseInspectContract.BUSINESS_PRODUCTION_DEPARTMENT, bean == null ? null : bean.getDirectorList(), "为本单位的\"行业主管\"企业", bean != null && isBinded(bean.getDirectorList()))));
                arrays.add(new MultipleItem(MultipleItem.ITEM_ADD_MANAGER, new BindManagerBean(R.mipmap.direct_manager_icon,
                        BaseInspectContract.BUSINESS_PRODUCTION_DIRECT_DEPARTMENT, bean == null ? null : bean.getSuperviseList(), "为本单位的\"直接监管责任\"企业", bean != null && isBinded(bean.getSuperviseList()))));
            } else if (2 == UserInfoManager.getAccountTypeId()) {
                arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_SMALL, new ImportantTagBean("属地监管", true)));
                arrays.add(new MultipleItem(MultipleItem.ITEM_ADD_MANAGER, new BindManagerBean(R.mipmap.supervise_icon,
                        BaseInspectContract.UNIT_TERRITORY_SUPERVISE, null, "为本区域监督（管理）企业", bean != null && bean.getTerritorySuperviseId() > 0)));
                arrays.add(new MultipleItem(MultipleItem.ITEM_ADD_MANAGER, new BindManagerBean(R.mipmap.supervise_people_icon,
                        BaseInspectContract.UNIT_UNIT_SUPERVISE_PEOPLE, bean == null ? null : bean.getSuperviseUserList(), "为本区域监督（管理）人", bean != null && isBinded(bean.getSuperviseUserList()))));
            } else if (3 == UserInfoManager.getAccountTypeId()) {
                arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_SMALL, new ImportantTagBean
                        ("监督(管理)人", true)));
                arrays.add(new MultipleItem(MultipleItem.ITEM_ADD_MANAGER, new BindManagerBean(R.mipmap.grid_icon,
                        BaseInspectContract.UNIT_GRID_SUPERVISE, null, "为本网格企业", bean != null && bean.getGridSuperviseId() > 0)));

            }


        }
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.REMARK, bean == null ? "" : bean.getRemarks(), false, 1);
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

    /**
     * 已绑定到主管单位
     *
     * @return
     */
    private boolean isBinded(List<IdNameBean.DataBean> arrays) {

        for (IdNameBean.DataBean array : arrays) {
            if (UserInfoManager.getDepartmentName().equals(array.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取监管列表中的内容
     *
     * @return
     */
    private String getListContent(List<IdNameBean.DataBean> arrays) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrays.size(); i++) {
            IdNameBean.DataBean array = arrays.get(i);
            if (i!=arrays.size()-1) {
                sb.append(array.getName()+"\n");
            }else {
                sb.append(array.getName());
            }
        }
        if (sb.toString().length()==0) {
            return "暂无";
        }
        return sb.toString();
    }

    /**
     * 获取规模大小
     * <p>
     * 1规上；2规下；3小微
     *
     * @return
     */
    private String getScaleName(int scale) {
        String scaleName = null;
        switch (scale) {
            case 1:
                scaleName = "规上";
                break;
            case 2:
                scaleName = "规下";
                break;
            case 3:
                scaleName = "小微";
                break;
            default:
                break;
        }
        return scaleName;
    }

    /**
     * 获取规模
     *
     * @return
     */
    public List<IdNameBean.DataBean> getScales() {
        List<IdNameBean.DataBean> arrays = new ArrayList<>();
        arrays.add(new IdNameBean.DataBean(1, "规上"));
        arrays.add(new IdNameBean.DataBean(2, "规下"));
        arrays.add(new IdNameBean.DataBean(3, "小微"));
        return arrays;
    }

    /**
     * 获取风险
     *
     * @return
     */
    public List<IdNameBean.DataBean> getRisks() {
        List<IdNameBean.DataBean> arrays = new ArrayList<>();
        arrays.add(new IdNameBean.DataBean(1, "高危"));
        arrays.add(new IdNameBean.DataBean(2, "较大"));
        arrays.add(new IdNameBean.DataBean(3, "一般"));
        arrays.add(new IdNameBean.DataBean(4, "无风险"));
        return arrays;
    }

    /**
     * 单位风险（1高危；2较大；3一般；4无风险）
     * <p>
     *
     * @return
     */
    private String getRiskName(int risk) {
        String scaleName = null;
        switch (risk) {
            case 1:
                scaleName = "高危";
                break;
            case 2:
                scaleName = "较大";
                break;
            case 3:
                scaleName = "一般";
                break;
            case 4:
                scaleName = "无风险";
                break;
            default:
                break;
        }
        return scaleName;
    }


    /**
     * 添加 单位详情
     *
     * @return
     */
    public List<MultipleItem> getUnitInfoMoreData(UnitDetailBean.DataBean bean, boolean isDetail) {
        List<MultipleItem> arrays = new ArrayList<>();
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_UNIT_NAME, bean == null ? "" :
                        bean.getName(),
                true,
                0);

        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_UNIT_UCC, bean == null ? "" :
                        bean.getUnifiedCreditCode(), true
                , 0);
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_SMALL, new ImportantTagBean
                (BaseInspectContract.INSPECTION_UNIT_AREA, true)));
        arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT,
                new TextKeyValueBean(BaseInspectContract.INSPECTION_UNIT_AREA,
                        String.format("%s%s%s", bean == null ? "" : bean.getTerritoryOneId(), ",", bean == null ? "" : bean.getTerritoryTwoId()), String.format("%s%s", "请选择",
                        BaseInspectContract.INSPECTION_UNIT_AREA), true, bean == null ? "" : bean.getTerritoryOneName(), bean == null ? "" : bean.getTerritoryTwoName())));
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

        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.UNIT_TYPE, bean == null ? "" : bean.getTypeName(), true, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.UNIT_SIZE, bean == null ? "" :
                getScaleName(bean.getScale()), true, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.UNIT_RISK, bean == null ? "" :
                getRiskName(bean.getRisk()), true, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.UNIT_DIRECTOR, bean == null ? "" :
                getListContent(bean.getDirectorList()), true, 1);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.UNIT_SUPERVISE, bean == null ? "" :
                getListContent(bean.getSuperviseList()), true, 1);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.UNIT_TERRITORY_SUPERVISE, bean == null ? "" :
                bean.getTerritoryName(), true, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.UNIT_UNIT_SUPERVISE_PEOPLE, bean == null ? "" :
                getListContent(bean.getSuperviseUserList()), true, 1);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.UNIT_GRID_SUPERVISE, bean == null ? "" :
                bean.getGridSuperviseName(), true, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.REMARK, bean == null ? "" : bean.getRemarks(), false, 1);

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

    /**
     * initTextType
     *
     * @param arrays
     * @param key
     */
    private void initTextSelectType(List<MultipleItem> arrays, String key, String id, String value,
                                    boolean isImportant, String other) {
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_SMALL, new ImportantTagBean
                (key, isImportant)));
        arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT,
                new TextKeyValueBean(key, value, id, String.format("%s%s", "请选择",
                        key), 0, isImportant, other)));
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


    /**
     * @param desTitle 描述的标题
     * @param picTitle 图片的标题
     * @param position
     * @return
     */
    public List<MultipleItem> addDesPicLayout(String desTitle, String picTitle, int position) {
        List<MultipleItem> arrays = new ArrayList<>();
        DesAndPicBean desAndPicBean = new DesAndPicBean();
        desAndPicBean.setBigTitle(picTitle);
        desAndPicBean.setPicRecycleBean(new PicRecycleBean(null));
        desAndPicBean.setImportantTagBean(new ImportantTagBean(desTitle, false));
        desAndPicBean.setTextKeyValueBean(new TextKeyValueBean(desTitle, "", "请输入" + desTitle, 1, false));
        arrays.add(new MultipleItem(MultipleItem.ITEM_DES_PIC, desAndPicBean));
        if (0 == position) {
            arrays.add(new MultipleItem(MultipleItem.ITEM_ADD_MORE, new AddDesPicBean(desTitle, picTitle, "增加更多")));
        }
        return arrays;
    }

    /**
     * 获取培训计划
     *
     * @return
     */
    public List<MultipleItem> getTrainPlansData(List<TrainPlanListBean.DataBean> dataBeans) {
        List<MultipleItem> arrays = new ArrayList<>();
        if (!dataBeans.isEmpty()) {
            for (TrainPlanListBean.DataBean problemsBean : dataBeans) {
                List<String> pics = new ArrayList<>();
                if (!TextUtils.isEmpty(problemsBean.getPhotoOne())) {
                    pics.add(problemsBean.getPhotoOne());
                }
                if (!TextUtils.isEmpty(problemsBean.getPhotoTwo())) {
                    pics.add(problemsBean.getPhotoTwo());
                }
                if (!TextUtils.isEmpty(problemsBean.getPhotoThree())) {
                    pics.add(problemsBean.getPhotoThree());
                }
                DesAndPicBean desAndPicBean = new DesAndPicBean();
                desAndPicBean.setBigTitle(problemsBean.getDescribe());
                desAndPicBean.setPicRecycleBean(new PicRecycleBean(pics));
//                desAndPicBean.setImportantTagBean(new ImportantTagBean("" , false));
//                desAndPicBean.setTextKeyValueBean(new TextKeyValueBean(BaseInspectContract.CHECK_DES , problemsBean.getDescribe(), "请输入" + BaseInspectContract.CHECK_DES, 1, false));
                arrays.add(new MultipleItem(MultipleItem.ITEM_DES_PIC, desAndPicBean));
            }
        }
        return arrays;
    }


    /**
     * 企业自查
     *
     * @param dataBean
     * @return
     */

    public List<TextKeyValueBean> getStartCheckData(UnitDetailBean.DataBean dataBean) {
        List<TextKeyValueBean> arrays = new ArrayList<>();
        arrays.add(new TextKeyValueBean("检查时间:", CalendarUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss")));
        arrays.add(new TextKeyValueBean("检查人员:", dataBean.getName() + UserInfoManager.getUserName() + "   单位自查"));
        arrays.add(new TextKeyValueBean("责任人:", dataBean.getPersonLiable()));
        arrays.add(new TextKeyValueBean("电话号码:", dataBean.getLiablePhone()));
        return arrays;
    }

    /**
     * 企业自查
     *
     * @param dataBean
     * @return
     */

    public List<TextKeyValueBean> getCheckDetailData(CheckDetailBean.DataBean dataBean) {
        List<TextKeyValueBean> arrays = new ArrayList<>();
        arrays.add(new TextKeyValueBean("检查时间:", dataBean.getCheckTime()));
        arrays.add(new TextKeyValueBean("检查人员:", dataBean.getDepartmentName() + dataBean.getNickname() + "   单位自查"));
        arrays.add(new TextKeyValueBean("责任人:", dataBean.getPersonLiable()));
        arrays.add(new TextKeyValueBean("电话号码:", dataBean.getPhoneNumber()));
        arrays.add(new TextKeyValueBean("是否合格:", 1 == dataBean.getQualified() ? "合格" : "不合格"));
        return arrays;
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

    public void ddUnit(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .ddUnit(requestBody)
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

    public void getEnterpriseInfo(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getEnterpriseInfo(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<UnitDetailBean>(getView()) {
                    @Override
                    public void onSuccess(UnitDetailBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o.getData());
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

    public void getEnterpriseInfoByUUID(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getEnterpriseInfoByUUID(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<UnitDetailBean>(getView()) {
                    @Override
                    public void onSuccess(UnitDetailBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o.getData());
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
    public void editUnitInfo(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .editUnitInfo(requestBody)
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
    public void deleteUnitManager(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .deleteUnitManager(requestBody)
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


    public void getResponseList(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getResponseList(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<IdNameBean>(getView()) {
                    @Override
                    public void onSuccess(IdNameBean o) {
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

    public void getResponseDetail(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getResponseDetail(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<ResponseDetailBean>(getView()) {
                    @Override
                    public void onSuccess(ResponseDetailBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o.getData());
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

    public void getRectifyNoticeList(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getRectifyNoticeList(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<RectifyNoticeListBean>(getView()) {
                    @Override
                    public void onSuccess(RectifyNoticeListBean o) {
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

    public void startInspect(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .startInspect(requestBody)
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

    public void getCheckList(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getCheckList(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<CheckRecordBean>(getView()) {
                    @Override
                    public void onSuccess(CheckRecordBean o) {
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

    public void getCheckRecordDetail(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getCheckRecordDetail(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<CheckDetailBean>(getView()) {
                    @Override
                    public void onSuccess(CheckDetailBean o) {
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

    public void addPunishInfo(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .addPunishInfo(requestBody)
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

    public void addRectifyNotice(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .addRectifyNotice(requestBody)
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

    public void getRectifyNoticeDetail(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getRectifyNoticeDetail(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<RectifyNoticeDeatilBean>(getView()) {
                    @Override
                    public void onSuccess(RectifyNoticeDeatilBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o.getData());
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

    public void getTrainPlanList(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getTrainPlanList(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<TrainPlanListBean>(getView()) {
                    @Override
                    public void onSuccess(TrainPlanListBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o.getData());
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

    public void addTrainPlans(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .addTrainPlans(requestBody)
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
    public void uploadPic(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .uploadPic(requestBody)
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

    public void searchAccountNature(RequestBody requestBody, String tag) {
        AppNetModule
                .createrRetrofit()
                .searchAccountNature(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<UnitsBean>(getView()) {
                    @Override
                    public void onSuccess(UnitsBean o) {
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

    public void getBusinessTypes(RequestBody requestBody, String tag) {
        AppNetModule
                .createrRetrofit()
                .getBusinessTypes(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<IdNameBean>(getView()) {
                    @Override
                    public void onSuccess(IdNameBean o) {
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
