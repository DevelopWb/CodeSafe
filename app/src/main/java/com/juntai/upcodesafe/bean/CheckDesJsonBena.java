package com.juntai.upcodesafe.bean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-10-14 17:07
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-14 17:07
 */
public class CheckDesJsonBena {

    public CheckDesJsonBena() {
    }

    public CheckDesJsonBena(String concreteProblem, String photoOne, String photoTwo, String photoThree) {
        this.concreteProblem = concreteProblem;
        this.photoOne = photoOne;
        this.photoTwo = photoTwo;
        this.photoThree = photoThree;
    }

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

    public String getPhotoThree() {
        return photoThree == null ? "" : photoThree;
    }

    public void setPhotoThree(String photoThree) {
        this.photoThree = photoThree == null ? "" : photoThree;
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
