package com.lft.kaoqinclient.http.request;

import com.hjq.http.config.IRequestApi;

import java.util.List;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/21 13:55
 */
public class TeacherAddCourseApi implements IRequestApi {

    @Override
    public String getApi() {
        return "teacher/add-course";
    }

    private String courseName;
    private String teacherUid;
    private List studentList;

    public TeacherAddCourseApi setCourseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    public TeacherAddCourseApi setTeacherUid(String teacherUid) {
        this.teacherUid = teacherUid;
        return this;
    }

    public TeacherAddCourseApi setStudentList(List studentList) {
        this.studentList = studentList;
        return this;
    }
}
