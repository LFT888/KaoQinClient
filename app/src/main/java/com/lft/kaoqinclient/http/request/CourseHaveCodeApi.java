package com.lft.kaoqinclient.http.request;

import com.hjq.http.config.IRequestApi;

/**
 * author LFT
 *
 * @date 2021/2/23 23:10
 */
public class CourseHaveCodeApi implements IRequestApi {

    @Override
    public String getApi() {
        return "student/have-code";
    }

    private Integer courseId;

    public CourseHaveCodeApi setCourseId(Integer courseId) {
        this.courseId = courseId;
        return this;
    }
}

