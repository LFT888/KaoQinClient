package com.lft.kaoqinclient.http.response;

/**
 * author LFT
 *
 * @date 2021/3/14 18:57
 */

public class SignInStartBean {
    private String courseTime;
    private String courseCode;

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}