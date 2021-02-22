package com.lft.kaoqinclient.http.response;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/21 14:35
 */
public class StudentInfoBean {
    private String uid;
    private String name;
    private String className;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
