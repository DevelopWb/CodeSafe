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
    private String bigTitle;
    private ItemFragmentBean itemFragmentBean;

    public DesAndPicBean() {
    }

    public DesAndPicBean(ImportantTagBean importantTagBean, TextKeyValueBean textKeyValueBean, String bigTitle, ItemFragmentBean itemFragmentBean) {
        this.importantTagBean = importantTagBean;
        this.textKeyValueBean = textKeyValueBean;
        this.bigTitle = bigTitle;
        this.itemFragmentBean = itemFragmentBean;
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

    public ItemFragmentBean getItemFragmentBean() {
        return itemFragmentBean;
    }

    public void setItemFragmentBean(ItemFragmentBean itemFragmentBean) {
        this.itemFragmentBean = itemFragmentBean;
    }
}
