package com.juntai.upcodesafe.bean;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  行业详情下面的企业
 * @CreateDate: 2021-10-21 15:46
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-21 15:46
 */
public class UnitOfInductryBean extends BaseResult {

    /**
     * error : null
     * data : [{"id":2,"name":"九州超市","address":"兰山区金雀山街道九州超市","picture":null,"typeName":"渔业","qualified":null,"longitude":null,"latitude":null},{"id":1,"name":"家家悦超市","address":"河东区九曲街道豪森广场","picture":"/logo/picture.jpg","typeName":"农业","qualified":1,"longitude":null,"latitude":null}]
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
         * id : 2
         * name : 九州超市
         * address : 兰山区金雀山街道九州超市
         * picture : null
         * typeName : 渔业
         * qualified : null
         * longitude : null
         * latitude : null
         */

        private int id;
        private String name;
        private String address;
        private String picture;
        private String typeName;
        //是否合格（1合格；2不合格）
        private int qualified;
        private String longitude;
        private String latitude;

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

        public String getTypeName() {
            return typeName == null ? "" : typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName == null ? "" : typeName;
        }

        public int getQualified() {
            return qualified;
        }

        public void setQualified(int qualified) {
            this.qualified = qualified;
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
    }
}
