package com.juntai.upcodesafe.bean;

/**
 * @Author: tobato
 * @Description: 作用描述  期限的实体类
 * @CreateDate: 2021-10-25 10:19
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-25 10:19
 */
public class ExpiredTimeBean {

    private String time;

    public ExpiredTimeBean(String time) {
        this.time = time;
    }

    public String getTime() {
        return time == null ? "" : time;
    }

    public void setTime(String time) {
        this.time = time == null ? "" : time;
    }
}
