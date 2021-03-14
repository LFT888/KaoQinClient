package com.lft.kaoqinclient.http.request;

import com.hjq.http.config.IRequestApi;

/**
 * author LFT
 *
 * @date 2021/3/7 14:56
 */
public class SignInStartApi implements IRequestApi {

    @Override
    public String getApi() {
        return "teacher/Sign-in-start";
    }

    private Integer courseId;
    private Integer time; // 过期时间

    public SignInStartApi setCourseId(Integer courseId) {
        this.courseId = courseId;
        return this;
    }

    public SignInStartApi setTime(Integer time) {
        this.time = time;
        return this;
    }
}
