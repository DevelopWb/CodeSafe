package com.juntai.upcodesafe.bean;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  检查记录
 * @CreateDate: 2021-10-15 14:00
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-15 14:00
 */
public class CheckRecordBean extends BaseResult {


    /**
     * error : null
     * data : [{"id":1,"checkTime":"2021-10-10 14:02:51","departmentName":"兰山区农业农村局","nickname":"哈哈哈","typeId":1,"checkType":2,"punish":1,"notice":0},{"id":2,"checkTime":"2021-10-10 14:03:26","departmentName":"兰山区农业农村局","nickname":"哈哈哈","typeId":1,"checkType":2,"punish":0,"notice":1}]
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
         * checkTime : 2021-10-10 14:02:51
         * departmentName : 兰山区农业农村局
         * nickname : 哈哈哈
         * typeId : 1
         * checkType : 2
         * punish : 1
         * notice : 0
         */
        private int id;//记录id

        private String checkTime;//检查时间

        private String departmentName;//检查部门名称

        private String nickname;//检查人名称

        private int typeId;//类型id（1常规检查；2专项检查；3验收检查）

        private int checkType;//检查类型（1企业自查；2监管检查；3属地检查；4网格检查）

        private int punish;//是否处罚（0未处罚）
        private int notice;//是否整改（0未整改）

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCheckTime() {
            return checkTime;
        }

        public void setCheckTime(String checkTime) {
            this.checkTime = checkTime;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public int getCheckType() {
            return checkType;
        }

        public void setCheckType(int checkType) {
            this.checkType = checkType;
        }

        public int getPunish() {
            return punish;
        }

        public void setPunish(int punish) {
            this.punish = punish;
        }

        public int getNotice() {
            return notice;
        }

        public void setNotice(int notice) {
            this.notice = notice;
        }
    }
}
