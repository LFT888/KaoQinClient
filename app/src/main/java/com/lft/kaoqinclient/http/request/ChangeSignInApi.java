package com.lft.kaoqinclient.http.request;

import com.hjq.http.config.IRequestApi;

/**
 * author LFT
 *
 * @date 2021/3/6 21:30
 */
public class ChangeSignInApi implements IRequestApi {

    @Override
    public String getApi() {
        return "teacher/change-sign-in";
    }

    private Integer courseId;
    private String courseTime;
    private String studentUid;
    private String signInInfo;


    public ChangeSignInApi setCourseId(Integer courseId) {
        this.courseId = courseId;
        return this;
    }

    public ChangeSignInApi setCourseTime(String courseTime) {
        this.courseTime = courseTime;
        return this;
    }

    public ChangeSignInApi setStudentUid(String studentUid) {
        this.studentUid = studentUid;
        return this;
    }

    public ChangeSignInApi setSignInInfo(String signInInfo) {
        this.signInInfo = signInInfo;
        return this;
    }
}
