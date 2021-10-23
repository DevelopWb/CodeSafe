package com.juntai.upcodesafe.bean;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-10-13 15:31
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-13 15:31
 */
public class NoticeBean extends BaseResult {


    /**
     * error : null
     * data : [{"id":1,"typeId":1,"title":"秦皇岛骊骅淀粉有限公司\u201c2.24\u201d重大粉尘爆炸事故","coverPicture":"/test/aaa.jpg","readNumber":3652,"gmtCreate":"2021-10-12 14:27:21"},{"id":2,"typeId":2,"title":"危化品事故安全警示教育片\u2014\u2014《生死之间》","coverPicture":"/test/bbb.jpg","readNumber":6526,"gmtCreate":"2021-10-12 14:31:25"}]
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
         * title : 秦皇岛骊骅淀粉有限公司“2.24”重大粉尘爆炸事故
         * coverPicture : /test/aaa.jpg
         * readNumber : 3652
         * gmtCreate : 2021-10-12 14:27:21
         */

        private int id;
        private int typeId;
        private String title;
        private String content;
        private String coverPicture;
        private String videoUrl;
        private int readNumber;
        private String gmtCreate;

        public String getVideoUrl() {
            return videoUrl == null ? "" : videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl == null ? "" : videoUrl;
        }

        public int getId() {
            return id;
        }

        public String getContent() {
            return content == null ? "" : content;
        }

        public void setContent(String content) {
            this.content = content == null ? "" : content;
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
