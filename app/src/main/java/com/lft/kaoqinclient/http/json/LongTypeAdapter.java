package com.lft.kaoqinclient.http.json;

import com.google.gson.stream.JsonReader;

import java.io.IOException;

/**
 * TODO
 * author LFT
 *
 * @date 2021/1/18 19:54
 */
public class LongTypeAdapter extends DoubleTypeAdapter {

    @Override
    public Number read(JsonReader in) throws IOException {
        Number number = super.read(in);
        if (number != null) {
            return number.longValue();
        }
        return null;
    }
}