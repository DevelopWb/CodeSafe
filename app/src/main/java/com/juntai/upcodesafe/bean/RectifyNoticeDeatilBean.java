package com.juntai.upcodesafe.bean;

import com.juntai.disabled.basecomponent.base.BaseResult;

/**
 * @Author: tobato
 * @Description: 作用描述  整改通知书详情
 * @CreateDate: 2021-10-17 17:09
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-17 17:09
 */
public class RectifyNoticeDeatilBean extends BaseResult {


    /**
     * error : null
     * data : {"content":"aaa","photoOne":"/test/aaa.jpg","photoTwo":"/test/aaa.jpg","photoThree":null}
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
         * content : aaa
         * photoOne : /test/aaa.jpg
         * photoTwo : /test/aaa.jpg
         * photoThree : null
         */

        private String content;
        private String photoOne;
        private String photoTwo;
        private String photoThree;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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
            return photoThree == null ? "" : photoThree;
        }

        public void setPhotoThree(String photoThree) {
            this.photoThree = photoThree == null ? "" : photoThree;
        }
    }
}
