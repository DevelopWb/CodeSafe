package com.juntai.upcodesafe.bean;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-10-16 17:26
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-16 17:26
 */
public class SearchBean extends BaseResult {


    /**
     * error : null
     * data : [{"name":"监管单位","searchType":1,"list":[{"id":1,"name":"家家悦超市","address":"河东区九曲街道豪森广场","picture":null,"longitude":null,"latitude":null}]},{"name":"在线教育","searchType":2,"list":[{"id":1,"typeId":1,"title":"家家悦2021年度培训计划","picture":"/logo/aaa.jpg","labelName":"在线教育-安全培训计划"}]},{"name":"通知公告","searchType":3,"list":[{"id":1,"title":"应急管理局2020年度法治政府建设情况报告家家悦","coverUrl":"/logo/aaa.jpg","content":"2020年，在省应急厅和市委、市政府的坚强领导下"}]}]
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
         * name : 监管单位
         * searchType : 1
         * list : [{"id":1,"name":"家家悦超市","address":"河东区九曲街道豪森广场","picture":null,"longitude":null,"latitude":null}]
         */

        private String name;
        private int searchType;
        private List<ListBean> list;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSearchType() {
            return searchType;
        }

        public void setSearchType(int searchType) {
            this.searchType = searchType;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1
             * name : 家家悦超市
             * address : 河东区九曲街道豪森广场
             * picture : null
             * longitude : null
             * latitude : null
             */

            private int id;
            private int typeId;
            private String name;
            private String title;
            private String labelName;
            private String coverUrl;
            private String content;
            private String address;
            private String picture;
            private String longitude;
            private String latitude;
            private int resultType;//搜索结果的类型 1监管单位 2 在线教育 3 通知公告
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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getPicture() {
                return picture == null ? "" : picture;
            }

            public void setPicture(String picture) {
                this.picture = picture == null ? "" : picture;
            }

            public String getLongitude() {
                return longitude == null ? "" : longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude == null ? "" : longitude;
            }

            public String getLatitude() {
                return latitude == null ? "" : latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude == null ? "" : latitude;
            }

            public int getResultType() {
                return resultType;
            }

            public void setResultType(int resultType) {
                this.resultType = resultType;
            }

            public int getTypeId() {
                return typeId;
            }

            public void setTypeId(int typeId) {
                this.typeId = typeId;
            }

            public String getTitle() {
                return title == null ? "" : title;
            }

            public void setTitle(String title) {
                this.title = title == null ? "" : title;
            }

            public String getLabelName() {
                return labelName == null ? "" : labelName;
            }

            public void setLabelName(String labelName) {
                this.labelName = labelName == null ? "" : labelName;
            }

            public String getCoverUrl() {
                return coverUrl == null ? "" : coverUrl;
            }

            public void setCoverUrl(String coverUrl) {
                this.coverUrl = coverUrl == null ? "" : coverUrl;
            }

            public String getContent() {
                return content == null ? "" : content;
            }

            public void setContent(String content) {
                this.content = content == null ? "" : content;
            }
        }
    }
}
