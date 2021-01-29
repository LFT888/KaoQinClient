package com.lft.kaoqinclient.http.request;

import com.hjq.http.annotation.HttpHeader;
import com.hjq.http.annotation.HttpRename;

/**
 * TODO
 * author LFT
 *
 * @date 2021/1/27 22:45
 */
public abstract class BaseApi {

    @HttpHeader
    @HttpRename("Authorization")
    private String token;

    public BaseApi setToken(String token) {
        this.token = "Bearer " + token;
        return this;
    }

}
