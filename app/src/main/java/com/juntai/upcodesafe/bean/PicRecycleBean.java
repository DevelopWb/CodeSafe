package com.juntai.upcodesafe.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-10-14 15:21
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-14 15:21
 */
public class PicRecycleBean {

    private List<String> pics;

    public PicRecycleBean(List<String> pics) {
        this.pics = pics;
    }

    public List<String> getPics() {
        if (pics == null) {
            return new ArrayList<>();
        }
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }
}
