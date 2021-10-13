package com.juntai.upcodesafe.bean;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-10-13 10:53
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-13 10:53
 */
public class TownListBean extends BaseResult {


    /**
     * error : null
     * data : [{"id":1,"name":"兰山区","childList":[{"id":2,"name":"半程镇"},{"id":3,"name":"金雀山街道"},{"id":4,"name":"银雀山街道"}]},{"id":5,"name":"河东区","childList":[{"id":6,"name":"九曲街道"}]}]
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
         * name : 兰山区
         * childList : [{"id":2,"name":"半程镇"},{"id":3,"name":"金雀山街道"},{"id":4,"name":"银雀山街道"}]
         */

        private int id;
        private String name;
        private List<ChildListBean> childList;

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

        public List<ChildListBean> getChildList() {
            return childList;
        }

        public void setChildList(List<ChildListBean> childList) {
            this.childList = childList;
        }

        public static class ChildListBean {
            /**
             * id : 2
             * name : 半程镇
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
        }
    }
}
