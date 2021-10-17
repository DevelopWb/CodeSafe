package com.juntai.upcodesafe.bean;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-10-16 17:29
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-16 17:29
 */
public class SearchResultBean  extends BaseResult {


    /**
     * error : null
     * data : {"datas":[{"id":1,"name":"家家悦超市","address":"河东区九曲街道豪森广场","picture":null,"longitude":null,"latitude":null}],"total":1,"listSize":1,"pageCount":1}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * datas : [{"id":1,"name":"家家悦超市","address":"河东区九曲街道豪森广场","picture":null,"longitude":null,"latitude":null}]
         * total : 1
         * listSize : 1
         * pageCount : 1
         */

        private int total;
        private int listSize;
        private int pageCount;
        private List<SearchBean.DataBean.ListBean> datas;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getListSize() {
            return listSize;
        }

        public void setListSize(int listSize) {
            this.listSize = listSize;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public List<SearchBean.DataBean.ListBean> getDatas() {
            return datas;
        }

        public void setDatas(List<SearchBean.DataBean.ListBean> datas) {
            this.datas = datas;
        }

    }
}
