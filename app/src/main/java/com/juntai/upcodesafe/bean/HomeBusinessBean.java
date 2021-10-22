package com.juntai.upcodesafe.bean;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  首页 行业
 * @CreateDate: 2021-10-18 14:22
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-18 14:22
 */
public class HomeBusinessBean extends BaseResult {


    /**
     * error : null
     * data : [{"name":"农业","total":1},{"name":"渔业","total":1}]
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
         * name : 农业
         * total : 1
         */

        private String name;
        private int total;
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }
}
