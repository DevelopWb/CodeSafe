package com.juntai.upcodesafe.bean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-10-14 17:07
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-14 17:07
 */
public class DesPicJsonBena {

    public DesPicJsonBena() {
    }


    /**
     * concreteProblem : 啦啦啦
     * photoOne : /test/aaa.jpg
     * photoTwo : /test/aaa.jpg
     * photoThree : null
     */

    private int unitId;
    private int userId;
    private String describe;
    private String concreteProblem;
    private String photoOne;
    private String photoTwo;
    private String photoThree;

    public String getPhotoThree() {
        return photoThree == null ? "" : photoThree;
    }

    public void setPhotoThree(String photoThree) {
        this.photoThree = photoThree == null ? "" : photoThree;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescribe() {
        return describe == null ? "" : describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? "" : describe;
    }

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

}
