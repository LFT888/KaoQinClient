package com.lft.kaoqinclient.http.request;

import com.hjq.http.config.IRequestApi;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/16 19:52
 */
public final class FindCourseByIdApi implements IRequestApi {

    @Override
    public String getApi() {
        return "student/find-course";
    }

    private String courseId;

    public FindCourseByIdApi setCourseId(String id) {
        this.courseId = id;
        return this;
    }
}
