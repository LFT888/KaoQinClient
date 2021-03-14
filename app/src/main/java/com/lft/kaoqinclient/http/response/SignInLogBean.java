package com.lft.kaoqinclient.http.response;

import java.util.List;

/**
 * author LFT
 *
 * @date 2021/3/7 16:47
 */
public class SignInLogBean {

    private List<SignInListBean> list;
    private String courseTime;
    private Integer shouldAttend; //应到人数
    private Integer actualNumber; //实到人数
    private Integer leaveNumber; //请假人数
    private Integer lateNumber; //迟到人数

    public List<SignInListBean> getList() {
        return list;
    }

    public void setList(List<SignInListBean> list) {
        this.list = list;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public Integer getShouldAttend() {
        return shouldAttend;
    }

    public void setShouldAttend(Integer shouldAttend) {
        this.shouldAttend = shouldAttend;
    }

    public Integer getActualNumber() {
        return actualNumber;
    }

    public void setActualNumber(Integer actualNumber) {
        this.actualNumber = actualNumber;
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
}
