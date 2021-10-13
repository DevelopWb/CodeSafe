package com.juntai.upcodesafe.bean;


import com.juntai.disabled.basecomponent.base.BaseResult;

/**
 * 个人信息
 * Created by Ma
 * on 2019/4/27
 */
public class UserBean extends BaseResult {

    /**
     * error : null
     * data : {"userId":2,"account":"18669505929","password":null,"nickname":null,"headPortrait":null,"typeId":0,"departmentId":0,"departmentName":null,"departmentSecondId":0,"territoryId":0,"territoryName":null,"unitId":0,"unitName":null,"gridId":0,"gridName":null,"state":3,"createTime":"2021-10-09 11:05:39","token":"4J5WRE5T2-4JBUWFC8Z6K9B2E5KQTQ3-NVRFGJUK-0"}
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
         * userId : 2
         * account : 18669505929
         * password : null
         * nickname : null
         * headPortrait : null
         * typeId : 0
         * departmentId : 0
         * departmentName : null
         * departmentSecondId : 0
         * territoryId : 0
         * territoryName : null
         * unitId : 0
         * unitName : null
         * gridId : 0
         * gridName : null
         * state : 3
         * createTime : 2021-10-09 11:05:39
         * token : 4J5WRE5T2-4JBUWFC8Z6K9B2E5KQTQ3-NVRFGJUK-0
         */

        private int userId;
        private String account;
        private String password;
        private String nickname;
        private String headPortrait;
        private int typeId;
        private int departmentId;
        private String departmentName;
        private int departmentSecondId;
        private int territoryId;
        private String territoryName;
        private int unitId;
        private String unitName;
        private int gridId;
        private String gridName;
        private int state;
        private String createTime;
        private String token;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password == null ? "" : password;
        }

        public String getNickname() {
            return nickname == null ? "姓名" : nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname == null ? "" : nickname;
        }

        public String getHeadPortrait() {
            return headPortrait == null ? "" : headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait == null ? "" : headPortrait;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public int getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(int departmentId) {
            this.departmentId = departmentId;
        }

        public String getDepartmentName() {
            return departmentName == null ? "" : departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName == null ? "" : departmentName;
        }

        public int getDepartmentSecondId() {
            return departmentSecondId;
        }

        public void setDepartmentSecondId(int departmentSecondId) {
            this.departmentSecondId = departmentSecondId;
        }

        public int getTerritoryId() {
            return territoryId;
        }

        public void setTerritoryId(int territoryId) {
            this.territoryId = territoryId;
        }

        public String getTerritoryName() {
            return territoryName == null ? "" : territoryName;
        }

        public void setTerritoryName(String territoryName) {
            this.territoryName = territoryName == null ? "" : territoryName;
        }

        public int getUnitId() {
            return unitId;
        }

        public void setUnitId(int unitId) {
            this.unitId = unitId;
        }

        public String getUnitName() {
            return unitName == null ? "" : unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName == null ? "" : unitName;
        }

        public int getGridId() {
            return gridId;
        }

        public void setGridId(int gridId) {
            this.gridId = gridId;
        }

        public String getGridName() {
            return gridName == null ? "" : gridName;
        }

        public void setGridName(String gridName) {
            this.gridName = gridName == null ? "" : gridName;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getCreateTime() {
            return createTime == null ? "" : createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime == null ? "" : createTime;
        }

        public String getToken() {
            return token == null ? "" : token;
        }

        public void setToken(String token) {
            this.token = token == null ? "" : token;
        }
    }
}
