package com.lft.kaoqinclient.http.request;

import com.hjq.http.config.IRequestApi;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/2 21:32
 */
public final class PasswordApi implements IRequestApi {

    @Override
    public String getApi() {
        return "user/password";
    }

    private String username;
    /** 密码 */
    private String password;

    public PasswordApi setPassword(String password) {
        this.password = password;
        return this;
    }

    public PasswordApi setUsername(String username) {
        this.username = username;
        return this;
    }
}
