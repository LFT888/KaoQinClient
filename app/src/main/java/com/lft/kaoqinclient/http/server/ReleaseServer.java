package com.lft.kaoqinclient.http.server;

import com.hjq.http.config.IRequestServer;

/**
 * TODO
 * author LFT
 *
 * @date 2021/1/21 22:36
 */
public class ReleaseServer implements IRequestServer {

    @Override
    public String getHost() {
        return "https://www.baidu.com/";
    }

    @Override
    public String getPath() {
        return "api/";
    }
}