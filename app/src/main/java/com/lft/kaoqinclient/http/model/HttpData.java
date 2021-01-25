package com.lft.kaoqinclient.http.model;

/**
 * TODO
 * author LFT
 *
 * @date 2021/1/18 19:49
 */
public class HttpData<T> {

    /** 返回码 */
    private int code;
    /** 提示语 */
    private String msg;
    /** 数据 */
    private T data;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
