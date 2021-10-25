package com.juntai.upcodesafe.bean;


import java.util.ArrayList;
import java.util.List;

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
    private CheckDetailBean.DataBean  checkDetailBean;
    private  String concreteProblemsJson;//描述和图片组合的json串

    public CheckDetailBean.DataBean getCheckDetailBean() {
        return checkDetailBean;
    }

    public void setCheckDetailBean(CheckDetailBean.DataBean checkDetailBean) {
        this.checkDetailBean = checkDetailBean;
    }


    public String getConcreteProblemsJson() {
        return concreteProblemsJson == null ? "" : concreteProblemsJson;
    }

    public void setConcreteProblemsJson(String concreteProblemsJson) {
        this.concreteProblemsJson = concreteProblemsJson == null ? "" : concreteProblemsJson;
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
