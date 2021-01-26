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
        return "http://192.168.33.1:8080/";
    }

    @Override
    public String getPath() {
        return "lft/";
    }
}