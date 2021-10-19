package com.juntai.upcodesafe.bean;

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
    private String managerInfo;
    private String btName;

    public BindManagerBean(int managerIcon, String managerName, String managerInfo, String btName) {
        this.managerIcon = managerIcon;
        this.managerName = managerName;
        this.managerInfo = managerInfo;
        this.btName = btName;
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

    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? "" : managerName;
    }

    public String getManagerInfo() {
        return managerInfo == null ? "" : managerInfo;
    }

    public void setManagerInfo(String managerInfo) {
        this.managerInfo = managerInfo == null ? "" : managerInfo;
    }

    public String getBtName() {
        return btName == null ? "" : btName;
    }

    public void setBtName(String btName) {
        this.btName = btName == null ? "" : btName;
    }
}
