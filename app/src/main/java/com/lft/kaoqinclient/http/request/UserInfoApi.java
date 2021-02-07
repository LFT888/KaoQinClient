package com.lft.kaoqinclient.http.request;

import com.hjq.http.config.IRequestApi;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/2 0:10
 */
public final class UserInfoApi implements IRequestApi {

    @Override
    public String getApi() {
        return "user/info";
    }


}