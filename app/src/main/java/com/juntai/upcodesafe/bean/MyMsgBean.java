package com.juntai.upcodesafe.bean;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-10-22 10:58
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-22 10:58
 */
public class MyMsgBean extends BaseResult {


    /**
     * error : null
     * data : [{"id":1,"contentId":1,"contentName":"到期整改","isRead":1,"publishTime":"2021-10-12"}]
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
         * contentId : 1
         * contentName : 到期整改
         * isRead : 1
         * publishTime : 2021-10-12
         */

        private int id;
        private int contentId;
        private String contentName;
        private int isRead;
        private String publishTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getContentId() {
            return contentId;
        }

        public void setContentId(int contentId) {
            this.contentId = contentId;
        }

        public String getContentName() {
            return contentName;
        }

        public void setContentName(String contentName) {
            this.contentName = contentName;
        }

        public int getIsRead() {
            return isRead;
        }

        public void setIsRead(int isRead) {
            this.isRead = isRead;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }
    }
}
