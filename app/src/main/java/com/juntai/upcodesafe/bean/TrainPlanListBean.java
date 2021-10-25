package com.juntai.upcodesafe.bean;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-10-16 9:07
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-16 9:07
 */
public class TrainPlanListBean extends BaseResult {


    /**
     * error : null
     * data : [{"describe":"哈哈哈","photoOne":"/test/bbb.jpg","photoTwo":"/test/bbb.jpg","photoThree":"/test/bbb.jpg"}]
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
         * describe : 哈哈哈
         * photoOne : /test/bbb.jpg
         * photoTwo : /test/bbb.jpg
         * photoThree : /test/bbb.jpg
         */
        private int unitId;
        private int userId;
        private String describe;
        private String photoOne;
        private String photoTwo;
        private String photoThree;

        public int getUnitId() {
            return unitId;
        }

        public void setUnitId(int unitId) {
            this.unitId = unitId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getPhotoOne() {
            return photoOne;
        }

        public void setPhotoOne(String photoOne) {
            this.photoOne = photoOne;
        }

        public String getPhotoTwo() {
            return photoTwo;
        }

        public void setPhotoTwo(String photoTwo) {
            this.photoTwo = photoTwo;
        }

        public String getPhotoThree() {
            return photoThree;
        }

        public void setPhotoThree(String photoThree) {
            this.photoThree = photoThree;
        }
    }
}
