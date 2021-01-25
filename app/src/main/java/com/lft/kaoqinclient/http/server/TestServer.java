package com.lft.kaoqinclient.http.server;

/**
 * TODO
 * author LFT
 *
 * @date 2021/1/21 22:37
 */
public class TestServer extends ReleaseServer {

    @Override
    public String getHost() {
        return "https://www.baidu.com/";
    }

    @Override
    public String getPath() {
        return "lft/";
    }
}