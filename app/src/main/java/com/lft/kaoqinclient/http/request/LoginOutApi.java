package com.lft.kaoqinclient.http.request;

import com.hjq.http.config.IRequestApi;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/4 22:53
 */
public final class LoginOutApi implements IRequestApi {

    @Override
    public String getApi() {
        return "userLogout";
    }

}
