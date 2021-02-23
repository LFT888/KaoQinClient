package com.lft.kaoqinclient.http.request;

import com.hjq.http.config.IRequestApi;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/22 21:09
 */
public class AddStudentByFileApi implements IRequestApi {

    @Override
    public String getApi() {
        return "teacher/create-course/file";
    }
}
