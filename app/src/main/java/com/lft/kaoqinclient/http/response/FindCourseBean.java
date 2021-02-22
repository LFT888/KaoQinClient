package com.lft.kaoqinclient.http.response;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/16 20:13
 */
public class FindCourseBean {

    private String courseName;
    private Integer courseId;
    private String teacherName;
    private Integer teacherId;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
