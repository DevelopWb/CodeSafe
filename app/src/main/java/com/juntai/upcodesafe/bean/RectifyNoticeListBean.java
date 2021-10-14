package com.juntai.upcodesafe.bean;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-10-14 10:10
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-14 10:10
 */
public class RectifyNoticeListBean extends BaseResult {


    /**
     * error : null
     * data : [{"gmtCreate":"2021年10月11日"}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * gmtCreate : 2021年10月11日
         */

        private String gmtCreate;

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }
    }
}
