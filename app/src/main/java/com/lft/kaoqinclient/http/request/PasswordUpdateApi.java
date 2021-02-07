package com.lft.kaoqinclient.http.request;

import com.hjq.http.config.IRequestApi;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/4 23:25
 */
public class PasswordUpdateApi implements IRequestApi {

    @Override
    public String getApi() {
        return "user/verify-password";
    }

    /** 登录密码 */
    private String password;


    public PasswordUpdateApi setPassword(String password) {
        this.password = password;
        return this;
    }
}