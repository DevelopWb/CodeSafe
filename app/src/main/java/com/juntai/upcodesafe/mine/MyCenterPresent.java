package com.juntai.upcodesafe.mine;


import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.MultipleItem;
import com.juntai.upcodesafe.bean.MyMenuBean;
import com.juntai.upcodesafe.mine.setting.MySettingActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe:
 * Create by zhangzhenlong
 * 2020/3/7
 * email:954101549@qq.com
 */
public class MyCenterPresent extends BasePresenter<IModel, MyCenterContract.ICenterView> implements MyCenterContract.ICenterPresent {

    private IView iView;

    @Override
    protected IModel createModel() {
        return null;
    }




    @Override
    public void initList() {
       }

    @Override
    public List<MultipleItem> getMenuBeans(){
        List<MultipleItem> menuBeans = new ArrayList<>();
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_DIVIDER,""));
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MENUS,new MyMenuBean("我的消息", 0, R.mipmap.my_message, MyCenterContract.CENTER_SETTING_TAG, true)));
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MENUS,new MyMenuBean("个人设置", 0, R.mipmap.my_set_list, MyCenterContract.CENTER_SETTING_TAG, false)));
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_DIVIDER,""));
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MENUS,new MyMenuBean("清理内存", 0, R.mipmap.my_set_list, MyCenterContract.SET_CLEAR_TAG, true)));
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MENUS,new MyMenuBean("检测更新", 0, R.mipmap.my_set_list, MyCenterContract.SET_UPDATE_TAG, false)));

        return menuBeans;
    }

}
