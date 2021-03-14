package com.lft.kaoqinclient.http.response;


import java.io.Serializable;

/**
 * author LFT
 *
 * @date 2021/3/6 12:38
 */
public class SignInListBean implements Serializable {
    private String uid;
    private String name;
    private String className;
    private String signInInfo;


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

    public String getSignInInfo() {
        return signInInfo;
    }

    public void setSignInInfo(String signInInfo) {
        this.signInInfo = signInInfo;
    }
}
