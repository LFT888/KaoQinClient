package com.lft.kaoqinclient.http.request;

import com.hjq.http.config.IRequestApi;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/22 21:05
 */
public class AddStudentByClassApi implements IRequestApi {

    @Override
    public String getApi() {
        return "teacher/create-course/class";
    }

    private String className;

    public AddStudentByClassApi setClassName(String className) {
        this.className = className;
        return this;
    }

}
