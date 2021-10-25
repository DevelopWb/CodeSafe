package com.juntai.upcodesafe.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述   绑定监管单位
 * @CreateDate: 2021-10-19 15:52
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-19 15:52
 */
public class BindManagerBean {

    private int managerIcon;
    private String managerName;
    private List<IdNameBean.DataBean> data;
    private String btName;
    private boolean isBound;//是否已经被绑定
    private int  unitId;

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public BindManagerBean(int managerIcon, String managerName, List<IdNameBean.DataBean> data, String btName, boolean isBound, int unitId) {
        this.managerIcon = managerIcon;
        this.managerName = managerName;
        this.data = data;
        this.btName = btName;
        this.isBound = isBound;
        this.unitId = unitId;
    }

    public boolean isBound() {
        return isBound;
    }

    public void setBound(boolean bound) {
        isBound = bound;
    }

    public int getManagerIcon() {
        return managerIcon;
    }

    public void setManagerIcon(int managerIcon) {
        this.managerIcon = managerIcon;
    }

    public String getManagerName() {
        return managerName == null ? "" : managerName;
    }

    public List<IdNameBean.DataBean> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<IdNameBean.DataBean> data) {
        this.data = data;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? "" : managerName;
    }

    public String getBtName() {
        return btName == null ? "" : btName;
    }

    public void setBtName(String btName) {
        this.btName = btName == null ? "" : btName;
    }
}
