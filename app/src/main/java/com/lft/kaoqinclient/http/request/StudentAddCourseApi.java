package com.lft.kaoqinclient.http.request;

import com.hjq.http.config.IRequestApi;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/16 20:29
 */
public class StudentAddCourseApi implements IRequestApi {

    @Override
    public String getApi() {
        return "student/add-course";
    }

    private String courseId;
    private String studentUid;

    public StudentAddCourseApi setCourseId(String id) {
        this.courseId = id;
        return this;
    }
    public StudentAddCourseApi setStudentUId(String id) {
        this.studentUid = id;
        return this;
    }
}