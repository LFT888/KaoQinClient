package com.lft.kaoqinclient.http.request;

import com.hjq.http.config.IRequestApi;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/3 23:30
 */
public final class StudentSignApi implements IRequestApi {

    @Override
    public String getApi() {
        return "student/checkin";
    }

    private Integer courseId;
    private String code;

    public StudentSignApi setCourseId(int courseId) {
        this.courseId = courseId;
        return this;
    }

    public StudentSignApi setCode(String code) {
        this.code = code;
        return this;
    }
}