package com.juntai.upcodesafe.home_page.baseinspect;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.DisplayUtil;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.base.selectPics.SelectPhotosFragment;
import com.juntai.upcodesafe.base.selectPics.ShowSelectedPicsAdapter;
import com.juntai.upcodesafe.bean.AddDesPicBean;
import com.juntai.upcodesafe.bean.BaseNormalRecyclerviewBean;
import com.juntai.upcodesafe.bean.DesAndPicBean;
import com.juntai.upcodesafe.bean.ImportantTagBean;
import com.juntai.upcodesafe.bean.ItemFragmentBean;
import com.juntai.upcodesafe.bean.LocationBean;
import com.juntai.upcodesafe.bean.MultipleItem;
import com.juntai.upcodesafe.bean.PicRecycleBean;
import com.juntai.upcodesafe.bean.TextKeyValueBean;

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
        this.isDetail = isDetail;
        this.mFragmentManager = mFragmentManager;
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {

        switch (item.getItemType()) {
            case MultipleItem.ITEM_HEAD_PIC:
                if (!isDetail) {
                    helper.addOnClickListener(R.id.form_head_pic_iv);
                }
//                BusinessPicBean headPicBean = (BusinessPicBean) item.getObject();
//                ImageView headIv = helper.getView(R.id.form_head_pic_iv);
//                String headPicPath = headPicBean.getPicPath();
//                if (!TextUtils.isEmpty(headPicPath)) {
//                    ImageLoadUtil.loadImageNoCache(mContext, headPicPath, headIv);
//                } else {
//                    ImageLoadUtil.loadImage(mContext, R.mipmap.two_inch_pic, headIv);
//                }
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
//                //正则
//                switch (editKey) {
//                    case BusinessContract.TABLE_TITLE_CONTACT_MODE:
//                        //联系方式
//                        editText.setInputType(InputType.TYPE_CLASS_PHONE);
//                        break;
//                    case BusinessContract.TABLE_TITLE_PHONE:
//                        //联系电话
//                        editText.setInputType(InputType.TYPE_CLASS_PHONE);
//                        break;
//                    case BusinessContract.TABLE_TITLE_MOBILE_NUM:
//                        //手机号码
//                        editText.setInputType(InputType.TYPE_CLASS_PHONE);
//                        break;
//                    case BusinessContract.TABLE_TITLE_HOUSE_PHONE:
//                        //住宅电话
//                        editText.setInputType(InputType.TYPE_CLASS_PHONE);
//                        break;
//                    case BusinessContract.TABLE_TITLE_WCHAT_PHONE:
//                        //微信手机号
//                        editText.setInputType(InputType.TYPE_CLASS_PHONE);
//                        break;
//                    case BusinessContract.TABLE_TITLE_CARD_NUM:
//                        //卡号
//                        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
//                        break;
//                    case BusinessContract.TABLE_TITLE_IDCARD:
//                        //身份证号
//                        break;
//                    case BusinessContract.TABLE_TITLE_CHILD_IDCARD:
//                        //儿童身份证号
//                        break;
//                    case BusinessContract.TABLE_TITLE_GUARDIAN_ID_CARD:
//                        //监护人身份证号
//                        break;
//                    case BusinessContract.TABLE_TITLE_AGE_FAMILY:
//                        //F年龄
//                        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
//                        break;
//                    case BusinessContract.TABLE_TITLE_AGE_PERSIONAL:
//                        //P年龄
//                        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
//                        break;
//                    case BusinessContract.TABLE_TITLE_DISABLE_CARD_ID:
//                        //残疾证号
//                        break;
//                    default:
//                        //输入类型为普通文本
//                        editText.setInputType(InputType.TYPE_CLASS_TEXT);
//                        break;
//                }

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
                textViewTv.setText(selectTextValue);
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
                helper.setText(R.id.add_more_item_tv,addDesPicBean.getAddTvValue());
                break;

            case MultipleItem.ITEM_DES_PIC:
                helper.setGone(R.id.item_des_title_tv,false);
                helper.setGone(R.id.important_tag_tv,false);
                helper.setGone(R.id.edit_value_et,false);
                DesAndPicBean desAndPicBean = (DesAndPicBean) item.getObject();
                if (desAndPicBean.getImportantTagBean() != null) {
                    helper.setGone(R.id.item_des_title_tv,true);
                    helper.setText(R.id.item_des_title_tv, desAndPicBean.getImportantTagBean().getTitleName());
                    helper.setGone(R.id.important_tag_tv, desAndPicBean.getImportantTagBean().isImportant());
                }
                if (desAndPicBean.getTextKeyValueBean() != null) {
                    helper.setGone(R.id.edit_value_et,true);
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
                if (pics.isEmpty()) {
                    pics.add("-1");
                }
                selectedPicsAdapter.setNewData((List<String>) picRecycleRv.getTag());
                picRecycleRv.setLayoutManager(picManager);
                selectedPicsAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        if (onPicRecyclerviewCallBack != null) {
                            onPicRecyclerviewCallBack.onItemClick(adapter,view,position,picRecycleBean,helper.getAdapterPosition());
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


    public  interface OnPicRecyclerviewCallBack{
        void onItemClick(BaseQuickAdapter adapter, View view, int position,PicRecycleBean picRecycleBean,int parentsPosition);
    }
}
