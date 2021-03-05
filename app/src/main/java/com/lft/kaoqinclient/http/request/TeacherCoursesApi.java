package com.lft.kaoqinclient.http.request;

import com.hjq.http.config.IRequestApi;

/**
 * author LFT
 *
 * @date 2021/2/28 15:04
 */
public class TeacherCoursesApi implements IRequestApi {
    //获取老师课程列表

    @Override
    public String getApi() {
        return "teacher/course-list";
    }

}
