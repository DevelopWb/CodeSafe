package com.juntai.upcodesafe.bean;


import okhttp3.MultipartBody;

/**
 * @Author: tobato
 * @Description: 作用描述  适配器中的数据
 * @CreateDate: 2021/5/11 11:12
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/11 11:12
 */
public class BaseAdapterDataBean {

    private  MultipartBody.Builder  builder;
    private UnitDetailBean.DataBean  unitDataBean;
    private  String concreteProblems;//描述和图片组合的json串

    public String getConcreteProblems() {
        return concreteProblems == null ? "" : concreteProblems;
    }

    public void setConcreteProblems(String concreteProblems) {
        this.concreteProblems = concreteProblems == null ? "" : concreteProblems;
    }

    public MultipartBody.Builder getBuilder() {
        return builder;
    }

    public void setBuilder(MultipartBody.Builder builder) {
        this.builder = builder;
    }

    public UnitDetailBean.DataBean getUnitDataBean() {
        return unitDataBean;
    }

    public void setUnitDataBean(UnitDetailBean.DataBean unitDataBean) {
        this.unitDataBean = unitDataBean;
    }
}
