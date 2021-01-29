package com.lft.kaoqinclient.http.server;

import com.hjq.http.config.IRequestServer;
import com.hjq.http.model.BodyType;

/**
 * TODO
 * author LFT
 *
 * @date 2021/1/21 22:37
 */
public class TestServer implements IRequestServer {

    @Override
    public String getHost() {
        return "http://192.168.33.1:8080/";
    }

    @Override
    public String getPath() {
        return "lft/";
    }

    @Override
    public BodyType getType() {
        // 参数以 Json 格式提交（默认是表单）
        return BodyType.JSON;
    }
}