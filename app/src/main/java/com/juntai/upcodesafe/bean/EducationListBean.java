package com.juntai.upcodesafe.bean;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-10-16 16:10
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-16 16:10
 */
public class EducationListBean extends BaseResult {


    /**
     * error : null
     * data : [{"id":1,"typeId":1,"title":"家家悦2021年度培训计划","coverPicture":"/logo/aaa.jpg","readNumber":0,"gmtCreate":"2021-10-12 17:18:02"}]
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
         * typeId : 1
         * title : 家家悦2021年度培训计划
         * coverPicture : /logo/aaa.jpg
         * readNumber : 0
         * gmtCreate : 2021-10-12 17:18:02
         */

        private int id;
        private int typeId;
        private String title;
        private String coverPicture;
        private int readNumber;
        private String gmtCreate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCoverPicture() {
            return coverPicture;
        }

        public void setCoverPicture(String coverPicture) {
            this.coverPicture = coverPicture;
        }

        public int getReadNumber() {
            return readNumber;
        }

        public void setReadNumber(int readNumber) {
            this.readNumber = readNumber;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }
    }
}
