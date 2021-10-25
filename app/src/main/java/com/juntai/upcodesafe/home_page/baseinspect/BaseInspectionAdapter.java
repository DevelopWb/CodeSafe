package com.juntai.upcodesafe.home_page.baseinspect;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.CalendarUtil;
import com.juntai.disabled.basecomponent.utils.DialogUtil;
import com.juntai.disabled.basecomponent.utils.DisplayUtil;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.PickerManager;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.selectPics.SelectPhotosFragment;
import com.juntai.upcodesafe.bean.AddDesPicBean;
import com.juntai.upcodesafe.bean.BaseNormalRecyclerviewBean;
import com.juntai.upcodesafe.bean.BindManagerBean;
import com.juntai.upcodesafe.bean.DesAndPicBean;
import com.juntai.upcodesafe.bean.ExpiredTimeBean;
import com.juntai.upcodesafe.bean.IdNameBean;
import com.juntai.upcodesafe.bean.ImportantTagBean;
import com.juntai.upcodesafe.bean.ItemFragmentBean;
import com.juntai.upcodesafe.bean.ItemSignBean;
import com.juntai.upcodesafe.bean.LocationBean;
import com.juntai.upcodesafe.bean.MultipleItem;
import com.juntai.upcodesafe.bean.PicRecycleBean;
import com.juntai.upcodesafe.bean.TextKeyValueBean;
import com.juntai.upcodesafe.home_page.unit.ManagerListAdapter;
import com.juntai.upcodesafe.utils.HawkProperty;
import com.juntai.upcodesafe.utils.StringTools;
import com.juntai.upcodesafe.utils.UrlFormatUtil;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/22 11:11
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/22 11:11
 */
public class BaseInspectionAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    private OnPicRecyclerviewCallBack onPicRecyclerviewCallBack;
    private boolean isDetail = false;//是否是详情模式
    private FragmentManager mFragmentManager;
    public String ITEM_HEAD_TAG2 = "场所于(";
    public String ITEM_FOOT_TAG2 = ")前改正";

    public void setOnPicRecyclerviewCallBack(OnPicRecyclerviewCallBack onPicRecyclerviewCallBack) {
        this.onPicRecyclerviewCallBack = onPicRecyclerviewCallBack;
    }

    /**
     * 控件失去焦点后  检测edittext控件输入内容的格式
     */
    interface OnCheckEdittextValueFormatCallBack {
        void checkEdittextValueFormat(TextKeyValueBean keyValueBean);
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public BaseInspectionAdapter(List<MultipleItem> data, boolean isDetail, FragmentManager mFragmentManager) {
        super(data);
        addItemType(MultipleItem.ITEM_HEAD_PIC, R.layout.item_layout_type_head_pic);
        addItemType(MultipleItem.ITEM_TITILE_BIG, R.layout.item_layout_type_title_big);
        addItemType(MultipleItem.ITEM_TITILE_SMALL, R.layout.item_layout_type_title_small);
        addItemType(MultipleItem.ITEM_EDIT, R.layout.item_layout_type_edit);
        addItemType(MultipleItem.ITEM_EDIT2, R.layout.item_layout_type_edit2);
        addItemType(MultipleItem.ITEM_SELECT, R.layout.item_layout_type_select);
        addItemType(MultipleItem.ITEM_FRAGMENT, R.layout.item_layout_type_fragment);
        addItemType(MultipleItem.ITEM_NORMAL_RECYCLEVIEW, R.layout.item_layout_type_recyclerview);
        addItemType(MultipleItem.ITEM_LOCATION, R.layout.item_layout_location);
        addItemType(MultipleItem.ITEM_DES_PIC, R.layout.item_des_pic);
        addItemType(MultipleItem.ITEM_ADD_MORE, R.layout.item_add_more);
        addItemType(MultipleItem.ITEM_ADD_MANAGER, R.layout.item_add_manager);
        addItemType(MultipleItem.ITEM_BUSINESS_TYPES, R.layout.item_business_types);
        addItemType(MultipleItem.ITEM_EXPIRE_TIME, R.layout.item_text);
        addItemType(MultipleItem.ITEM_SIGN, R.layout.item_layout_type_sign);


        this.isDetail = isDetail;
        this.mFragmentManager = mFragmentManager;
    }

    private void initSpannableText(TextView textView, String content) {

        //  只展示已经选择的项目
        ExpiredTimeBean expiredTimeBean = (ExpiredTimeBean) textView.getTag();
        SpannableStringBuilder builder = new SpannableStringBuilder();
        SpannableString spannableString = new SpannableString(content);
        //点击事件
        spannableString.setSpan(new ClickableSpan() {
                                    @Override
                                    public void onClick(@NonNull View widget) {
                                        if (isDetail) {
                                            return;
                                        }
                                        //第一个选择时间
                                        PickerManager.getInstance().showTimePickerView(mContext,
                                                PickerManager.getInstance().getTimeType("day"),
                                                "选择日期", new PickerManager.OnTimePickerTimeSelectedListener() {
                                                    @Override
                                                    public void onTimeSelect(Date date, View v) {
                                                        String str =
                                                                content.replaceFirst(content.substring(getFirstHeadIndex(content, ITEM_HEAD_TAG2),
                                                                        getFirstFootIndex(content, ITEM_FOOT_TAG2)),
                                                                        CalendarUtil.getTimeFromDate("yyyy-MM-dd",
                                                                                date));
                                                        initSpannableText(textView, str);
                                                        expiredTimeBean.setTime(CalendarUtil.getTimeFromDate("yyyy-MM" +
                                                                "-dd", date));
                                                    }
                                                });
                                    }
                                }, getFirstHeadIndex(content, ITEM_HEAD_TAG2), getFirstFootIndex(content,
                ITEM_FOOT_TAG2),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //下划线
        spannableString.setSpan(new UnderlineSpan(), getFirstHeadIndex(content, ITEM_HEAD_TAG2),
                getFirstFootIndex(content, ITEM_FOOT_TAG2),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字颜色
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.colorAccent)),
                getFirstHeadIndex(content, ITEM_HEAD_TAG2),
                getFirstFootIndex(content, ITEM_FOOT_TAG2),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        builder.append(spannableString);
        textView.setText(builder);
        // 添加这一行之后，指定区域文字点击事件才会生效
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private int getFirstHeadIndex(String content, String str) {
        return content.indexOf(str) + str.length();
    }

    private int getLastHeadIndex(String content, String str) {
        return content.lastIndexOf(str) + str.length();
    }

    private int getFirstFootIndex(String content, String str) {
        return content.indexOf(str);
    }

    private int getLastFootIndex(String content, String str) {
        return content.lastIndexOf(str);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {

        switch (item.getItemType()) {

            case MultipleItem.ITEM_SIGN:
                ItemSignBean signBean = (ItemSignBean) item.getObject();
                if (signBean.isCanSign()) {
                    helper.addOnClickListener(R.id.sign_ll);
                }
                int gravity = signBean.getLayoutGravity();
                LinearLayout signLl = helper.getView(R.id.item_sign_ll);
                ImageView signIv = helper.getView(R.id.sign_name_iv);
                if (0 == gravity) {
                    helper.setGone(R.id.sign_tag, true);
                    signLl.setGravity(Gravity.LEFT);
                } else {
                    helper.setGone(R.id.sign_tag, false);
                    signLl.setGravity(Gravity.RIGHT);
                }
                helper.setText(R.id.sign_name_tv, signBean.getSignName());
                if (StringTools.isStringValueOk(signBean.getSignPicPath())) {
                    if (signBean.getSignPicPath().contains(BaseInspectionActivity.SDCARD_TAG)) {
                        ImageLoadUtil.loadImage(mContext, signBean.getSignPicPath(),
                                signIv);
                    }else {
                        ImageLoadUtil.loadImage(mContext, UrlFormatUtil.getImageOriginalUrl(signBean.getSignPicPath()),
                                signIv);
                    }

                }
                break;


            case MultipleItem.ITEM_EXPIRE_TIME:
                ExpiredTimeBean expiredTimeBean = (ExpiredTimeBean) item.getObject();
                String des = mContext.getString(R.string.check_summarize);
                helper.setText(R.id.single_text_tv, des);
                /**
                 * 总结
                 */

                TextView summarizeTv = helper.getView(R.id.single_text_tv);
                summarizeTv.setTag(expiredTimeBean);
                String content = mContext.getString(R.string.check_summarize);
                if (!TextUtils.isEmpty(expiredTimeBean.getTime())) {
                    content = content.replaceFirst(content.substring(getFirstHeadIndex(content, ITEM_HEAD_TAG2),
                            getFirstFootIndex(content, ITEM_FOOT_TAG2)), expiredTimeBean.getTime());
                }
                initSpannableText(summarizeTv, content);

                break;
            case MultipleItem.ITEM_BUSINESS_TYPES:
                TextKeyValueBean businessTypeBean = (TextKeyValueBean) item.getObject();
                AutoCompleteTextView autoCompleteTextView = helper.getView(R.id.business_type_tv);
                autoCompleteTextView.setText(businessTypeBean.getValue());
                List<IdNameBean.DataBean> businessTypes = Hawk.get(HawkProperty.BUSINESS_TYPES_KEY);
                DataValueAdapter dataValueAdapter = new DataValueAdapter(mContext, businessTypes);
                autoCompleteTextView.setAdapter(dataValueAdapter);
                dataValueAdapter.setOnNoDataCallBack(new DataValueAdapter.OnNoDataCallBack() {
                    @Override
                    public void noSearchedData() {
                        autoCompleteTextView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                autoCompleteTextView.setText("");
                            }
                        }, 1000);
                    }
                });
                autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        IdNameBean.DataBean idnameBean = dataValueAdapter.getDatas().get(position);
                        autoCompleteTextView.setText(idnameBean.getName());
                        autoCompleteTextView.setSelection(autoCompleteTextView.getText().length());
                        businessTypeBean.setValue(idnameBean.getName());
                        businessTypeBean.setIds(String.valueOf(idnameBean.getId()));

                    }
                });
                break;
            case MultipleItem.ITEM_ADD_MANAGER:
                BindManagerBean bindManagerBean = (BindManagerBean) item.getObject();
                helper.setText(R.id.manager_name_iv, bindManagerBean.getManagerName());
                if (bindManagerBean.getData() == null || bindManagerBean.getData().isEmpty()) {
                    helper.setGone(R.id.managers_name_rv, false);
                } else {
                    helper.setGone(R.id.managers_name_rv, true);
                }
                RecyclerView bindManagerRv = helper.getView(R.id.managers_name_rv);
                GridLayoutManager bindmanager = new GridLayoutManager(mContext, 2);
                bindManagerRv.setLayoutManager(bindmanager);
                ManagerListAdapter managerListAdapter = new ManagerListAdapter(R.layout.single_text_layout);
                bindManagerRv.setAdapter(managerListAdapter);
                managerListAdapter.setNewData(bindManagerBean.getData());
                helper.addOnClickListener(R.id.manager_bind_tv);
                if (bindManagerBean.isBound()) {
                    helper.setTextColor(R.id.manager_bind_tv, ContextCompat.getColor(mContext, R.color.red));
                    helper.setText(R.id.manager_bind_tv, "移除" + bindManagerBean.getBtName());
                } else {
                    helper.setTextColor(R.id.manager_bind_tv, ContextCompat.getColor(mContext, R.color.colorAccent));
                    helper.setText(R.id.manager_bind_tv, "添加" + bindManagerBean.getBtName());
                }

                ImageLoadUtil.loadImage(mContext, bindManagerBean.getManagerIcon(), helper.getView(R.id.manager_logo_iv));
                break;
            case MultipleItem.ITEM_HEAD_PIC:
                if (!isDetail) {
                    helper.addOnClickListener(R.id.form_head_pic_iv);
                }
                break;
            case MultipleItem.ITEM_TITILE_BIG:
                helper.setText(R.id.item_big_title_tv, (String) item.getObject());
                break;
            case MultipleItem.ITEM_TITILE_SMALL:
                ImportantTagBean importantTagBean = (ImportantTagBean) item.getObject();
                helper.setGone(R.id.important_tag_tv, importantTagBean.isImportant());
                helper.setText(R.id.item_small_title_tv, importantTagBean.getTitleName());
                break;
            case MultipleItem.ITEM_EDIT:
                TextKeyValueBean textValueEditBean = (TextKeyValueBean) item.getObject();
                EditText editText = helper.getView(R.id.edit_value_et);
                initEditTextData(textValueEditBean, editText);
                String editKey = ((TextKeyValueBean) editText.getTag()).getKey();

                break;
            case MultipleItem.ITEM_EDIT2:
                TextKeyValueBean textValueEditBean2 = (TextKeyValueBean) item.getObject();
                EditText editText2 = helper.getView(R.id.value_et);
                initEdittextFocuseStatus(editText2);
                TextView textView2 = helper.getView(R.id.key_tv);
                editText2.setTag(textValueEditBean2);
                addTextChange(editText2);
                editText2.setText(textValueEditBean2.getValue());
                String editKeyTv = textValueEditBean2.getKey();

                textView2.setText(editKeyTv);
                break;
            case MultipleItem.ITEM_LOCATION:
                LocationBean locationBean = (LocationBean) item.getObject();
                if (!isDetail) {
                    helper.addOnClickListener(R.id.location_ll);
                    helper.setGone(R.id.location_iv, true);
                } else {
                    helper.setGone(R.id.location_iv, false);
                }
                if (!TextUtils.isEmpty(locationBean.getAddress())) {

                    helper.setText(R.id.location_tv, locationBean.getAddress());
                }

                break;
            case MultipleItem.ITEM_SELECT:
                TextKeyValueBean textValueSelectBean = (TextKeyValueBean) item.getObject();
                TextView textViewTv = helper.getView(R.id.select_value_tv);
                String selectTextValue = textValueSelectBean.getValue();
                if (!isDetail) {
                    helper.addOnClickListener(R.id.select_value_tv);
                    helper.addOnClickListener(R.id.tool_pic_iv);
                    helper.setBackgroundRes(R.id.select_value_tv, R.drawable.stroke_gray_square_bg);
                    helper.setGone(R.id.select_arrow_right_iv, true);
                } else {
                    helper.setGone(R.id.select_arrow_right_iv, false);
                    helper.setBackgroundRes(R.id.select_value_tv, R.drawable.sp_filled_gray_lighter);
                }
                textViewTv.setTag(textValueSelectBean);
                textViewTv.setHint(textValueSelectBean.getHint());
                if (selectTextValue.contains("\\n")) {
                    selectTextValue = selectTextValue.replace("\\n", "\n");
                }
                textViewTv.setText(selectTextValue.trim());
                break;
            case MultipleItem.ITEM_NORMAL_RECYCLEVIEW:
                //recycleview

                BaseNormalRecyclerviewBean baseNormalRecyclerviewBean = (BaseNormalRecyclerviewBean) item.getObject();
                RecyclerView recyclerView = helper.getView(R.id.item_normal_rv);
                LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL
                        , false);
                BaseQuickAdapter adapter = baseNormalRecyclerviewBean.getAdapter();
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(manager);
                switch (baseNormalRecyclerviewBean.getType()) {
                    case BaseInspectContract.BASE_RECYCLERVIEW_TYPE_TEXT_VALUE:
                        List<TextKeyValueBean> arrays = (List<TextKeyValueBean>) baseNormalRecyclerviewBean.getObject();
                        adapter.setNewData(arrays);
                        break;
                    case BaseInspectContract.BASE_RECYCLERVIEW_TYPE_RESPONSIBILITY_CONTENT:
                        List<String> pics = (List<String>) baseNormalRecyclerviewBean.getObject();
                        adapter.setNewData(pics);
                        break;
                    default:
                        break;
                }
                break;
            case MultipleItem.ITEM_FRAGMENT:
                ItemFragmentBean fragmentBean = (ItemFragmentBean) item.getObject();
                initFragmentData(fragmentBean);
                break;
            case MultipleItem.ITEM_ADD_MORE:
                AddDesPicBean addDesPicBean = (AddDesPicBean) item.getObject();
                helper.addOnClickListener(R.id.add_more_item_ll);
                helper.setText(R.id.add_more_item_tv, addDesPicBean.getAddTvValue());
                break;

            case MultipleItem.ITEM_DES_PIC:
                helper.setGone(R.id.item_des_title_tv, false);
                helper.setGone(R.id.important_tag_tv, false);
                helper.setGone(R.id.edit_value_et, false);
                DesAndPicBean desAndPicBean = (DesAndPicBean) item.getObject();
                if (desAndPicBean.getImportantTagBean() != null) {
                    helper.setGone(R.id.item_des_title_tv, true);
                    helper.setText(R.id.item_des_title_tv, desAndPicBean.getImportantTagBean().getTitleName());
                    helper.setGone(R.id.important_tag_tv, desAndPicBean.getImportantTagBean().isImportant());
                }
                if (desAndPicBean.getTextKeyValueBean() != null) {
                    helper.setGone(R.id.edit_value_et, true);
                    EditText desEt = helper.getView(R.id.edit_value_et);
                    initEditTextData(desAndPicBean.getTextKeyValueBean(), desEt);
                }

                helper.setText(R.id.item_big_title_tv, desAndPicBean.getBigTitle());
                PicRecycleBean picRecycleBean = desAndPicBean.getPicRecycleBean();
                List<String> pics = picRecycleBean.getPics();
                RecyclerView picRecycleRv = helper.getView(R.id.item_normal_rv);
                picRecycleRv.setTag(pics);
                GridLayoutManager picManager = new GridLayoutManager(mContext, 3) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
                HorPicsAdapter selectedPicsAdapter = new HorPicsAdapter(R.layout.show_selected_pic_item);
                selectedPicsAdapter.setDelateable(!isDetail);
                picRecycleRv.setAdapter(selectedPicsAdapter);
                if (pics == null || pics.isEmpty()) {
                    pics.add("-1");
                }
                selectedPicsAdapter.setNewData((List<String>) picRecycleRv.getTag());
                picRecycleRv.setLayoutManager(picManager);
                selectedPicsAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        if (onPicRecyclerviewCallBack != null) {
                            onPicRecyclerviewCallBack.onItemClick(adapter, view, position, picRecycleBean, helper.getAdapterPosition());
                        }
                    }

                });
                break;


            default:
                break;
        }
    }

    private void initEditTextData(TextKeyValueBean textValueEditBean, EditText editText) {
        if (isDetail) {
            editText.setClickable(false);
            editText.setFocusable(false);
        } else {
            editText.setClickable(true);
            editText.setFocusable(true);
        }
        int editType = textValueEditBean.getType();
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) editText.getLayoutParams();
        if (0 == editType) {
            lp.height = DisplayUtil.dp2px(mContext, 32);
            editText.setGravity(Gravity.CENTER_VERTICAL);
            editText.setSingleLine(true);
        } else {
            lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            editText.setMinimumHeight(DisplayUtil.dp2px(mContext, 100));
            editText.setGravity(Gravity.TOP);
            editText.setSingleLine(false);
        }
        editText.setLayoutParams(lp);
        editText.setTag(textValueEditBean);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                TextKeyValueBean editBean = (TextKeyValueBean) editText.getTag();
                String str = s.toString().trim();
                editBean.setValue(str);
            }
        });
        editText.setHint(textValueEditBean.getHint());
        editText.setText(textValueEditBean.getValue());
    }

    private void initFragmentData(ItemFragmentBean fragmentBean) {
        //上传材料时 多选照片
        SelectPhotosFragment fragment = (SelectPhotosFragment) mFragmentManager.findFragmentById(R.id.photo_fg);
        fragment.setObject(fragmentBean);
        fragment.setSpanCount(fragmentBean.getmSpanCount())
                .setPhotoDelateable(!isDetail)
                .setType(fragmentBean.getType())
                .setMaxCount(fragmentBean.getmMaxCount())
                .setShowTag(fragmentBean.isShowTag()).setOnPicLoadSuccessCallBack(new SelectPhotosFragment.OnPicLoadSuccessCallBack() {
            @Override
            public void loadSuccess(List<String> icons) {
                ItemFragmentBean picBean = (ItemFragmentBean) fragment.getObject();
                picBean.setFragmentPics(icons);
            }
        });
        if (fragmentBean.getFragmentPics().size() > 0) {
            fragment.setIcons(fragmentBean.getFragmentPics());
        }

    }

    private void initEdittextFocuseStatus(EditText editText) {
        if (isDetail) {
            editText.setClickable(false);
            editText.setFocusable(false);
        } else {
            editText.setClickable(true);
            editText.setFocusable(true);
        }
    }


    /**
     * 配置最大长度
     *
     * @param editText
     * @param i2
     */
    private void setMaxLength(EditText editText, int i2) {
        //手动设置maxLength为18
        InputFilter[] filters = {new InputFilter.LengthFilter(i2)};
        editText.setFilters(filters);
    }

    private void addTextChange(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                switch (editText.getId()) {
                    case R.id.value_et:
                        TextKeyValueBean editBean = (TextKeyValueBean) editText.getTag();
                        editBean.setValue(s.toString().trim());
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 配置view的margin属性
     */
    private void setMargin(View view, int left, int top, int right, int bottom) {
        left = DisplayUtil.dp2px(mContext, left);
        top = DisplayUtil.dp2px(mContext, top);
        right = DisplayUtil.dp2px(mContext, right);
        bottom = DisplayUtil.dp2px(mContext, bottom);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(view.getLayoutParams());
        layoutParams.setMargins(left, top, right, bottom);
        view.setLayoutParams(layoutParams);
    }


    public interface OnPicRecyclerviewCallBack {
        void onItemClick(BaseQuickAdapter adapter, View view, int position, PicRecycleBean picRecycleBean, int parentsPosition);
    }
}
