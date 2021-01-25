package com.lft.kaoqinclient.http.json;

import com.google.gson.stream.JsonReader;

import java.io.IOException;

/**
 * TODO
 * author LFT
 *
 * @date 2021/1/18 19:53
 */
public class FloatTypeAdapter extends DoubleTypeAdapter {

    @Override
    public Number read(JsonReader in) throws IOException {
        Number number = super.read(in);
        if (number != null) {
            return number.floatValue();
        }
        return null;
    }
}