package com.juntai.upcodesafe.bean;

/**
 * @Author: tobato
 * @Description: 作用描述  描述和图片结合
 * @CreateDate: 2021-10-14 11:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-14 11:55
 */
public class DesAndPicBean {
    private ImportantTagBean importantTagBean;
    private TextKeyValueBean textKeyValueBean;
    private PicRecycleBean picRecycleBean;
    private String bigTitle;

    public DesAndPicBean() {
    }

    public PicRecycleBean getPicRecycleBean() {
        return picRecycleBean;
    }

    public void setPicRecycleBean(PicRecycleBean picRecycleBean) {
        this.picRecycleBean = picRecycleBean;
    }

    public ImportantTagBean getImportantTagBean() {
        return importantTagBean;
    }

    public void setImportantTagBean(ImportantTagBean importantTagBean) {
        this.importantTagBean = importantTagBean;
    }

    public TextKeyValueBean getTextKeyValueBean() {
        return textKeyValueBean;
    }

    public void setTextKeyValueBean(TextKeyValueBean textKeyValueBean) {
        this.textKeyValueBean = textKeyValueBean;
    }

    public String getBigTitle() {
        return bigTitle == null ? "" : bigTitle;
    }

    public void setBigTitle(String bigTitle) {
        this.bigTitle = bigTitle == null ? "" : bigTitle;
    }

}
