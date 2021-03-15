package com.lft.kaoqinclient.http.request;

import com.hjq.http.config.IRequestApi;

/**
 * author LFT
 *
 * @date 2021/3/15 13:25
 */
public class SignInLogApi implements IRequestApi {

    @Override
    public String getApi() {
        return "teacher/sign-in-log";
    }

    private Integer courseId;

    public SignInLogApi setCourseId(Integer courseId) {
        this.courseId = courseId;
        return this;
    }

}