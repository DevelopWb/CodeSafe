package com.juntai.upcodesafe.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  单位详情
 * @CreateDate: 2021/5/18 14:26
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/18 14:26
 */
public class UnitDetailBean extends BaseResult {


    /**
     * error : null
     * data : {"id":2,"name":"九州超市","uuid":"1097b6cf290b445f96a7d078d70434f2","userId":null,"nickname":null,"territoryOneId":1,"territoryOneName":"兰山区","territoryTwoId":3,"territoryTwoName":"金雀山街道","address":"九州超市","unifiedCreditCode":"2961261sa6d16as1d6a","legal":null,"legalPhone":null,"type":2,"typeName":"渔业","scale":null,"risk":null,"personLiable":null,"liablePhone":null,"remarks":null,"gpsAddress":null,"longitude":null,"latitude":null,"territorySuperviseId":3,"territorySuperviseName":"金雀山街道","gridSuperviseId":2,"gridSuperviseName":"王庄网格","coverPicture":null,"photoTwo":null,"photoThree":null,"photoFour":null,"photoFive":null,"photoSix":null,"qrCode":"/unit_qr_code/九州超市1f8617473d214add94e64a6798478e0f.jpg","createAccount":null,"gmtCreate":"2021-10-09 14:03:02","directorList":[{"id":1,"name":"兰山区农业农村局"},{"id":3,"name":"兰山区应急管理局"}],"superviseList":[{"id":1,"name":"兰山区农业农村局"},{"id":2,"name":"兰山区自然资源局"},{"id":3,"name":"兰山区应急管理局"}],"superviseUserList":[{"id":1,"name":"哈哈哈"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * id : 2
         * name : 九州超市
         * uuid : 1097b6cf290b445f96a7d078d70434f2
         * userId : null
         * nickname : null
         * territoryOneId : 1
         * territoryOneName : 兰山区
         * territoryTwoId : 3
         * territoryTwoName : 金雀山街道
         * address : 九州超市
         * unifiedCreditCode : 2961261sa6d16as1d6a
         * legal : null
         * legalPhone : null
         * type : 2
         * typeName : 渔业
         * scale : null
         * risk : null
         * personLiable : null
         * liablePhone : null
         * remarks : null
         * gpsAddress : null
         * longitude : null
         * latitude : null
         * territorySuperviseId : 3
         * territorySuperviseName : 金雀山街道
         * gridSuperviseId : 2
         * gridSuperviseName : 王庄网格
         * coverPicture : null
         * photoTwo : null
         * photoThree : null
         * photoFour : null
         * photoFive : null
         * photoSix : null
         * qrCode : /unit_qr_code/九州超市1f8617473d214add94e64a6798478e0f.jpg
         * createAccount : null
         * gmtCreate : 2021-10-09 14:03:02
         * directorList : [{"id":1,"name":"兰山区农业农村局"},{"id":3,"name":"兰山区应急管理局"}]
         * superviseList : [{"id":1,"name":"兰山区农业农村局"},{"id":2,"name":"兰山区自然资源局"},{"id":3,"name":"兰山区应急管理局"}]
         * superviseUserList : [{"id":1,"name":"哈哈哈"}]
         */

        private int id;
        private String name;
        private String uuid;
        private String userId;
        private String nickname;
        private int territoryOneId;
        private String territoryOneName;
        private String territoryName;
        private int territoryTwoId;
        private String territoryTwoName;
        private String address;
        private String unifiedCreditCode;
        private String legal;
        private String ids;
        private String legalPhone;
        private int type;
        private String typeName;
        private int  scale;
        private int risk;
        private String personLiable;
        private String liablePhone;
        private String remarks;
        private String gpsAddress;
        private String longitude;
        private String latitude;
        private int territorySuperviseId;
        private String territorySuperviseName;
        private int gridSuperviseId;
        private String gridSuperviseName;
        private String coverPicture;
        private String photoTwo;
        private String photoThree;
        private String photoFour;
        private String photoFive;
        private String photoSix;
        private String qrCode;
        private String createAccount;
        private String gmtCreate;
        private List<DirectorListBean> directorList;
        private List<SuperviseListBean> superviseList;
        private List<SuperviseUserListBean> superviseUserList;
        private int isAdd;//0未添加
        public String getIds() {
            return ids == null ? "" : ids;
        }

        public int getIsAdd() {
            return isAdd;
        }

        public void setIsAdd(int isAdd) {
            this.isAdd = isAdd;
        }

        public void setIds(String ids) {
            this.ids = ids == null ? "" : ids;
        }

        public String getTerritoryName() {
            return territoryName == null ? "" : territoryName;
        }

        public void setTerritoryName(String territoryName) {
            this.territoryName = territoryName == null ? "" : territoryName;
        }

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

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getTerritoryOneId() {
            return territoryOneId;
        }

        public void setTerritoryOneId(int territoryOneId) {
            this.territoryOneId = territoryOneId;
        }

        public String getTerritoryOneName() {
            return territoryOneName;
        }

        public void setTerritoryOneName(String territoryOneName) {
            this.territoryOneName = territoryOneName;
        }

        public int getTerritoryTwoId() {
            return territoryTwoId;
        }

        public void setTerritoryTwoId(int territoryTwoId) {
            this.territoryTwoId = territoryTwoId;
        }

        public String getTerritoryTwoName() {
            return territoryTwoName;
        }

        public void setTerritoryTwoName(String territoryTwoName) {
            this.territoryTwoName = territoryTwoName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getUnifiedCreditCode() {
            return unifiedCreditCode;
        }

        public void setUnifiedCreditCode(String unifiedCreditCode) {
            this.unifiedCreditCode = unifiedCreditCode;
        }

        public String getLegal() {
            return legal;
        }

        public void setLegal(String legal) {
            this.legal = legal;
        }

        public String getLegalPhone() {
            return legalPhone;
        }

        public void setLegalPhone(String legalPhone) {
            this.legalPhone = legalPhone;
        }


        public String getPersonLiable() {
            return personLiable;
        }

        public void setPersonLiable(String personLiable) {
            this.personLiable = personLiable;
        }

        public String getLiablePhone() {
            return liablePhone;
        }

        public void setLiablePhone(String liablePhone) {
            this.liablePhone = liablePhone;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getGpsAddress() {
            return gpsAddress;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTypeName() {
            return typeName == null ? "" : typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName == null ? "" : typeName;
        }

        public int getScale() {
            return scale;
        }

        public void setScale(int scale) {
            this.scale = scale;
        }

        public int getRisk() {
            return risk;
        }

        public void setRisk(int risk) {
            this.risk = risk;
        }

        public void setGpsAddress(String gpsAddress) {
            this.gpsAddress = gpsAddress;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public int getTerritorySuperviseId() {
            return territorySuperviseId;
        }

        public void setTerritorySuperviseId(int territorySuperviseId) {
            this.territorySuperviseId = territorySuperviseId;
        }

        public String getTerritorySuperviseName() {
            return territorySuperviseName;
        }

        public void setTerritorySuperviseName(String territorySuperviseName) {
            this.territorySuperviseName = territorySuperviseName;
        }

        public int getGridSuperviseId() {
            return gridSuperviseId;
        }

        public void setGridSuperviseId(int gridSuperviseId) {
            this.gridSuperviseId = gridSuperviseId;
        }

        public String getGridSuperviseName() {
            return gridSuperviseName;
        }

        public void setGridSuperviseName(String gridSuperviseName) {
            this.gridSuperviseName = gridSuperviseName;
        }

        public String getCoverPicture() {
            return coverPicture;
        }

        public void setCoverPicture(String coverPicture) {
            this.coverPicture = coverPicture;
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

        public String getPhotoFour() {
            return photoFour;
        }

        public void setPhotoFour(String photoFour) {
            this.photoFour = photoFour;
        }

        public String getPhotoFive() {
            return photoFive;
        }

        public void setPhotoFive(String photoFive) {
            this.photoFive = photoFive;
        }

        public String getPhotoSix() {
            return photoSix;
        }

        public void setPhotoSix(String photoSix) {
            this.photoSix = photoSix;
        }

        public String getQrCode() {
            return qrCode;
        }

        public void setQrCode(String qrCode) {
            this.qrCode = qrCode;
        }

        public String getCreateAccount() {
            return createAccount;
        }

        public void setCreateAccount(String createAccount) {
            this.createAccount = createAccount;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public List<DirectorListBean> getDirectorList() {
            return directorList;
        }

        public void setDirectorList(List<DirectorListBean> directorList) {
            this.directorList = directorList;
        }

        public List<SuperviseListBean> getSuperviseList() {
            return superviseList;
        }

        public void setSuperviseList(List<SuperviseListBean> superviseList) {
            this.superviseList = superviseList;
        }

        public List<SuperviseUserListBean> getSuperviseUserList() {
            return superviseUserList;
        }

        public void setSuperviseUserList(List<SuperviseUserListBean> superviseUserList) {
            this.superviseUserList = superviseUserList;
        }

        public static class DirectorListBean implements Parcelable {
            /**
             * id : 1
             * name : 兰山区农业农村局
             */

            private int id;
            private String name;

            public DirectorListBean(int id, String name) {
                this.id = id;
                this.name = name;
            }

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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.name);
            }

            public DirectorListBean() {
            }

            protected DirectorListBean(Parcel in) {
                this.id = in.readInt();
                this.name = in.readString();
            }

            public static final Parcelable.Creator<DirectorListBean> CREATOR = new Parcelable.Creator<DirectorListBean>() {
                @Override
                public DirectorListBean createFromParcel(Parcel source) {
                    return new DirectorListBean(source);
                }

                @Override
                public DirectorListBean[] newArray(int size) {
                    return new DirectorListBean[size];
                }
            };
        }

        public static class SuperviseListBean implements Parcelable {
            /**
             * id : 1
             * name : 兰山区农业农村局
             */

            private int id;
            private String name;

            public SuperviseListBean(int id, String name) {
                this.id = id;
                this.name = name;
            }

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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.name);
            }

            public SuperviseListBean() {
            }

            protected SuperviseListBean(Parcel in) {
                this.id = in.readInt();
                this.name = in.readString();
            }

            public static final Parcelable.Creator<SuperviseListBean> CREATOR = new Parcelable.Creator<SuperviseListBean>() {
                @Override
                public SuperviseListBean createFromParcel(Parcel source) {
                    return new SuperviseListBean(source);
                }

                @Override
                public SuperviseListBean[] newArray(int size) {
                    return new SuperviseListBean[size];
                }
            };
        }

        public static class SuperviseUserListBean implements Parcelable {
            public SuperviseUserListBean(int id, String name) {
                this.id = id;
                this.name = name;
            }

            /**
             * id : 1
             * name : 哈哈哈
             */

            private int id;
            private String name;

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


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.name);
            }

            public SuperviseUserListBean() {
            }

            protected SuperviseUserListBean(Parcel in) {
                this.id = in.readInt();
                this.name = in.readString();
            }

            public static final Parcelable.Creator<SuperviseUserListBean> CREATOR = new Parcelable.Creator<SuperviseUserListBean>() {
                @Override
                public SuperviseUserListBean createFromParcel(Parcel source) {
                    return new SuperviseUserListBean(source);
                }

                @Override
                public SuperviseUserListBean[] newArray(int size) {
                    return new SuperviseUserListBean[size];
                }
            };
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.name);
            dest.writeString(this.uuid);
            dest.writeString(this.userId);
            dest.writeString(this.nickname);
            dest.writeInt(this.territoryOneId);
            dest.writeString(this.territoryOneName);
            dest.writeString(this.territoryName);
            dest.writeInt(this.territoryTwoId);
            dest.writeString(this.territoryTwoName);
            dest.writeString(this.address);
            dest.writeString(this.unifiedCreditCode);
            dest.writeString(this.legal);
            dest.writeString(this.ids);
            dest.writeString(this.legalPhone);
            dest.writeInt(this.type);
            dest.writeString(this.typeName);
            dest.writeInt(this.scale);
            dest.writeInt(this.risk);
            dest.writeString(this.personLiable);
            dest.writeString(this.liablePhone);
            dest.writeString(this.remarks);
            dest.writeString(this.gpsAddress);
            dest.writeString(this.longitude);
            dest.writeString(this.latitude);
            dest.writeInt(this.territorySuperviseId);
            dest.writeString(this.territorySuperviseName);
            dest.writeInt(this.gridSuperviseId);
            dest.writeString(this.gridSuperviseName);
            dest.writeString(this.coverPicture);
            dest.writeString(this.photoTwo);
            dest.writeString(this.photoThree);
            dest.writeString(this.photoFour);
            dest.writeString(this.photoFive);
            dest.writeString(this.photoSix);
            dest.writeString(this.qrCode);
            dest.writeString(this.createAccount);
            dest.writeString(this.gmtCreate);
            dest.writeTypedList(this.directorList);
            dest.writeTypedList(this.superviseList);
            dest.writeTypedList(this.superviseUserList);
            dest.writeInt(this.isAdd);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.name = in.readString();
            this.uuid = in.readString();
            this.userId = in.readString();
            this.nickname = in.readString();
            this.territoryOneId = in.readInt();
            this.territoryOneName = in.readString();
            this.territoryName = in.readString();
            this.territoryTwoId = in.readInt();
            this.territoryTwoName = in.readString();
            this.address = in.readString();
            this.unifiedCreditCode = in.readString();
            this.legal = in.readString();
            this.ids = in.readString();
            this.legalPhone = in.readString();
            this.type = in.readInt();
            this.typeName = in.readString();
            this.scale = in.readInt();
            this.risk = in.readInt();
            this.personLiable = in.readString();
            this.liablePhone = in.readString();
            this.remarks = in.readString();
            this.gpsAddress = in.readString();
            this.longitude = in.readString();
            this.latitude = in.readString();
            this.territorySuperviseId = in.readInt();
            this.territorySuperviseName = in.readString();
            this.gridSuperviseId = in.readInt();
            this.gridSuperviseName = in.readString();
            this.coverPicture = in.readString();
            this.photoTwo = in.readString();
            this.photoThree = in.readString();
            this.photoFour = in.readString();
            this.photoFive = in.readString();
            this.photoSix = in.readString();
            this.qrCode = in.readString();
            this.createAccount = in.readString();
            this.gmtCreate = in.readString();
            this.directorList = in.createTypedArrayList(DirectorListBean.CREATOR);
            this.superviseList = in.createTypedArrayList(SuperviseListBean.CREATOR);
            this.superviseUserList = in.createTypedArrayList(SuperviseUserListBean.CREATOR);
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
    }
}
