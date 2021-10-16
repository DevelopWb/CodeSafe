package com.juntai.upcodesafe.bean;

/**
 * @Author: tobato
 * @Description: 作用描述  添加描述 图片空间的实体类
 * @CreateDate: 2021-10-16 11:16
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-16 11:16
 */
public class AddDesPicBean {
    private String desTitle;
    private String picTitle;
    private  String addTvValue;//这个控件的内容

    public AddDesPicBean(String desTitle, String picTitle, String addTvValue) {
        this.desTitle = desTitle;
        this.picTitle = picTitle;
        this.addTvValue = addTvValue;
    }

    public String getDesTitle() {
        return desTitle == null ? "" : desTitle;
    }

    public void setDesTitle(String desTitle) {
        this.desTitle = desTitle == null ? "" : desTitle;
    }

    public String getPicTitle() {
        return picTitle == null ? "" : picTitle;
    }

    public void setPicTitle(String picTitle) {
        this.picTitle = picTitle == null ? "" : picTitle;
    }

    public String getAddTvValue() {
        return addTvValue == null ? "" : addTvValue;
    }

    public void setAddTvValue(String addTvValue) {
        this.addTvValue = addTvValue == null ? "" : addTvValue;
    }
}
