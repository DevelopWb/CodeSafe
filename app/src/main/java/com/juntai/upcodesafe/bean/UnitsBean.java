package com.juntai.upcodesafe.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.contrarywind.interfaces.IPickerViewData;
import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  所有的单位企业
 * @CreateDate: 2021-10-12 10:46
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-12 10:46
 */
public class UnitsBean  extends BaseResult {


    /**
     * error : null
     * data : [{"id":1,"territoryId":0,"name":"兰山区农业农村局","address":null,"logo":null}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable, IPickerViewData {
        public DataBean(int id, String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * id : 1
         * territoryId : 0
         * name : 兰山区农业农村局
         * address : null
         * logo : null
         */

        private int id;
        private int territoryId;
        private String name;
        private String address;
        private String logo;
        private int isAdd;//0未添加

        public int getIsAdd() {
            return isAdd;
        }

        public void setIsAdd(int isAdd) {
            this.isAdd = isAdd;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTerritoryId() {
            return territoryId;
        }

        public void setTerritoryId(int territoryId) {
            this.territoryId = territoryId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address == null ? "" : address;
        }

        public void setAddress(String address) {
            this.address = address == null ? "" : address;
        }

        public String getLogo() {
            return logo == null ? "" : logo;
        }

        public void setLogo(String logo) {
            this.logo = logo == null ? "" : logo;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeInt(this.territoryId);
            dest.writeString(this.name);
            dest.writeString(this.address);
            dest.writeString(this.logo);
            dest.writeInt(this.isAdd);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.territoryId = in.readInt();
            this.name = in.readString();
            this.address = in.readString();
            this.logo = in.readString();
            this.isAdd = in.readInt();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        @Override
        public String getPickerViewText() {
            return name;
        }
    }
}
