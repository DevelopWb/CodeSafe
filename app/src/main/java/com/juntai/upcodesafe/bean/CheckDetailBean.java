package com.juntai.upcodesafe.bean;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-10-14 10:39
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-14 10:39
 */
public class CheckDetailBean extends BaseResult {


    /**
     * error : null
     * data : {"id":1,"checkTime":"2021-10-10 14:02","departmentName":"兰山区农业农村局","nickname":"哈哈哈","personLiable":"张三","phoneNumber":"18669505929","qualified":2,"rectifyTime":"2021-10-10","signPhoto":"/test/aaa.jpg","checkType":2,"concreteProblems":[{"concreteProblem":"啦啦啦","photoOne":"/test/aaa.jpg","photoTwo":"/test/aaa.jpg","photoThree":null},{"concreteProblem":"哈哈哈","photoOne":"/test/bbb.jpg","photoTwo":"/test/bbb.jpg","photoThree":null}],"unitNotice":{"content":"aaa","photoOne":"/test/aaa.jpg","photoTwo":"/test/aaa.jpg","photoThree":null},"unitPunish":{"content":"哎哎","photoOne":"/test/bbb.jpg","photoTwo":"/test/bbb.jpg","photoThree":null}}
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
         * id : 1
         * checkTime : 2021-10-10 14:02
         * departmentName : 兰山区农业农村局
         * nickname : 哈哈哈
         * personLiable : 张三
         * phoneNumber : 18669505929
         * qualified : 2
         * rectifyTime : 2021-10-10
         * signPhoto : /test/aaa.jpg
         * checkType : 2
         * concreteProblems : [{"concreteProblem":"啦啦啦","photoOne":"/test/aaa.jpg","photoTwo":"/test/aaa.jpg","photoThree":null},{"concreteProblem":"哈哈哈","photoOne":"/test/bbb.jpg","photoTwo":"/test/bbb.jpg","photoThree":null}]
         * unitNotice : {"content":"aaa","photoOne":"/test/aaa.jpg","photoTwo":"/test/aaa.jpg","photoThree":null}
         * unitPunish : {"content":"哎哎","photoOne":"/test/bbb.jpg","photoTwo":"/test/bbb.jpg","photoThree":null}
         */

        private int id;
        private String checkTime;
        private String departmentName;
        private String nickname;
        private String personLiable;
        private String phoneNumber;
        private int qualified;
        private String rectifyTime;
        private String signPhoto;
        private int checkType;
        private UnitNoticeBean unitNotice;
        private UnitPunishBean unitPunish;
        private List<ConcreteProblemsBean> concreteProblems;

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

        public String getPersonLiable() {
            return personLiable;
        }

        public void setPersonLiable(String personLiable) {
            this.personLiable = personLiable;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public int getQualified() {
            return qualified;
        }

        public void setQualified(int qualified) {
            this.qualified = qualified;
        }

        public String getRectifyTime() {
            return rectifyTime;
        }

        public void setRectifyTime(String rectifyTime) {
            this.rectifyTime = rectifyTime;
        }

        public String getSignPhoto() {
            return signPhoto;
        }

        public void setSignPhoto(String signPhoto) {
            this.signPhoto = signPhoto;
        }

        public int getCheckType() {
            return checkType;
        }

        public void setCheckType(int checkType) {
            this.checkType = checkType;
        }

        public UnitNoticeBean getUnitNotice() {
            return unitNotice;
        }

        public void setUnitNotice(UnitNoticeBean unitNotice) {
            this.unitNotice = unitNotice;
        }

        public UnitPunishBean getUnitPunish() {
            return unitPunish;
        }

        public void setUnitPunish(UnitPunishBean unitPunish) {
            this.unitPunish = unitPunish;
        }

        public List<ConcreteProblemsBean> getConcreteProblems() {
            return concreteProblems;
        }

        public void setConcreteProblems(List<ConcreteProblemsBean> concreteProblems) {
            this.concreteProblems = concreteProblems;
        }

        public static class UnitNoticeBean {
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

        public static class UnitPunishBean {
            /**
             * content : 哎哎
             * photoOne : /test/bbb.jpg
             * photoTwo : /test/bbb.jpg
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

        public static class ConcreteProblemsBean {
            /**
             * concreteProblem : 啦啦啦
             * photoOne : /test/aaa.jpg
             * photoTwo : /test/aaa.jpg
             * photoThree : null
             */

            private String concreteProblem;
            private String photoOne;
            private String photoTwo;
            private String photoThree;

            public String getConcreteProblem() {
                return concreteProblem;
            }

            public void setConcreteProblem(String concreteProblem) {
                this.concreteProblem = concreteProblem;
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
}
