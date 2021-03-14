package com.lft.kaoqinclient.http.response;

import java.io.Serializable;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/1 19:32
 */

public class CourseBean implements Serializable {

    private Integer id;
    private Integer courseId;
    private Integer studentId;
    private String sid;
    private String teacherName;
    private String courseName;
    private Integer leaveNumber;
    private Integer lateNumber;
    private Integer signNumber;
    private Double attendanceRate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getLeaveNumber() {
        return leaveNumber;
    }

    public void setLeaveNumber(Integer leaveNumber) {
        this.leaveNumber = leaveNumber;
    }

    public Integer getLateNumber() {
        return lateNumber;
    }

    public void setLateNumber(Integer lateNumber) {
        this.lateNumber = lateNumber;
    }

    public Integer getSignNumber() {
        return signNumber;
    }

    public void setSignNumber(Integer signNumber) {
        this.signNumber = signNumber;
    }

    public Double getAttendanceRate() {
        return attendanceRate;
    }

    public void setAttendanceRate(Double attendanceRate) {
        this.attendanceRate = attendanceRate;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
