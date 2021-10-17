package com.juntai.upcodesafe.bean;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-10-16 15:59
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-16 15:59
 */
public class LableBean extends BaseResult {


    /**
     * error : null
     * data : [{"id":1,"labelName":"安全培训计划"},{"id":2,"labelName":"自主培训"},{"id":3,"labelName":"重点岗位培训"}]
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
         * id : 1
         * labelName : 安全培训计划
         */

        private int id;
        private String labelName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLabelName() {
            return labelName;
        }

        public void setLabelName(String labelName) {
            this.labelName = labelName;
        }
    }
}
