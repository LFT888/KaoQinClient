package com.lft.kaoqinclient.http.request;

import com.hjq.http.config.IRequestApi;

/**
 * author LFT
 *
 * @date 2021/3/6 14:19
 */
public class SignInTableApi implements IRequestApi {

    @Override
    public String getApi() {
        return "teacher/Sign-in-table";
    }

    private Integer courseId;
    private String courseTime;

    public SignInTableApi setCourseId(Integer courseId) {
        this.courseId = courseId;
        return this;
    }

    public SignInTableApi setCourseTime(String courseTime) {
        this.courseTime = courseTime;
        return this;
    }
}

