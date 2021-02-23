package com.lft.kaoqinclient.http.response;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/21 13:15
 */
public class TeacherCourseBean {

    private String courseName;
    private Integer courseId;
    private String courseSid;
    private String studentMessage;

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

    public String getCourseSid() {
        return courseSid;
    }

    public void setCourseSid(String courseSid) {
        this.courseSid = courseSid;
    }

    public String getStudentMessage() {
        return studentMessage;
    }

    public void setStudentMessage(String studentMessage) {
        this.studentMessage = studentMessage;
    }
}
