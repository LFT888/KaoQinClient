package com.lft.kaoqinclient.http.request;

import com.hjq.http.annotation.HttpHeader;
import com.hjq.http.config.IRequestApi;

/**
 * TODO
 * author LFT
 *
 * @date 2021/1/26 21:46
 */
public final class StudentCoursesApi implements IRequestApi {
    //获取学生列表

    @Override
    public String getApi() {
        return "student/course-list";
    }

}
