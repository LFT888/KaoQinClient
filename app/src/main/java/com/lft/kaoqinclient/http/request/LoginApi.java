package com.lft.kaoqinclient.http.request;

import com.hjq.http.config.IRequestApi;

/**
 * TODO
 * author LFT
 *
 * @date 2021/1/24 14:21
 */
public final class LoginApi implements IRequestApi {

    @Override
    public String getApi() {
        return "login";
    }

    /** 学工号 */
    private String username;
    /** 登录密码 */
    private String password;

    public LoginApi setId(String username) {
        this.username = username;
        return this;
    }

    public LoginApi setPassword(String password) {
        this.password = password;
        return this;
    }
}