package com.lft.kaoqinclient.http.request;

import com.hjq.http.config.IRequestApi;

/**
 * author LFT
 *
 * @date 2021/3/7 20:27
 */
public class CourseSignedApi implements IRequestApi {

    @Override
    public String getApi() {
        return "teacher/is-signed-in";
    }

    private Integer courseId;

    public CourseSignedApi setCourseId(Integer courseId) {
        this.courseId = courseId;
        return this;
    }
}