package com.lft.kaoqinclient.http.request;

import com.hjq.http.config.IRequestApi;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/22 21:10
 */
public class AddStudentByIdApi implements IRequestApi {

    @Override
    public String getApi() {
        return "teacher/create-course/id";
    }

    private String sid;

    public AddStudentByIdApi setSid(String sid) {
        this.sid = sid;
        return this;
    }
}

