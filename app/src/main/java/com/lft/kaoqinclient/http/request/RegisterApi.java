package com.lft.kaoqinclient.http.request;


import com.hjq.http.config.IRequestApi;

/**
 * TODO
 * author LFT
 *
 * @date 2021/1/27 23:06
 */
public final class RegisterApi implements IRequestApi {

    @Override
    public String getApi() {
        return "registered";
    }

    /** 学工号 */
    private String userId;
    /** 名字*/
    private String name;
    /** 密码 */
    private String password;
    private String sex;
    /** email */
    private String email;
    private int identity;

    private String className;


    public RegisterApi setEmail(String email) {
        this.email = email;
        return this;
    }


    public RegisterApi setUserId(String id) {
        this.userId = id;
        return this;
    }

    public RegisterApi setPassword(String password) {
        this.password = password;
        return this;
    }

    public RegisterApi setName(String name) {
        this.name = name;
        return this;
    }

    public RegisterApi setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public RegisterApi setIdentity(int identity) {
        this.identity = identity;
        return this;
    }

    public RegisterApi setClassName(String className) {
        this.className = className;
        return this;
    }
}