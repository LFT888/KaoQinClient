package com.lft.kaoqinclient.http.request;

import com.hjq.http.config.IRequestApi;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/2 21:30
 */
public final class VerifyCodeApi implements IRequestApi {

    @Override
    public String getApi() {
        return "user/verify-code";
    }

    /** email */
    private String email;
    /** 验证码 */
    private String code;

    public VerifyCodeApi setEmail(String email) {
        this.email = email;
        return this;
    }

    public VerifyCodeApi setCode(String code) {
        this.code = code;
        return this;
    }
}
