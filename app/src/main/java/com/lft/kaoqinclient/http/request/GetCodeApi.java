package com.lft.kaoqinclient.http.request;

import com.hjq.http.config.IRequestApi;

/**
 * TODO
 * author LFT
 *
 * @date 2021/1/27 23:06
 */
public final class GetCodeApi implements IRequestApi {

    @Override
    public String getApi() {
        return "user/code-get";
    }

    /** email */
    private String email;

    public GetCodeApi setEmail(String email) {
        this.email = email;
        return this;
    }
}
