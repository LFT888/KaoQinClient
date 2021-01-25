package com.lft.kaoqinclient.http.json;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * TODO
 * author LFT
 *
 * @date 2021/1/18 19:54
 */
public class StringTypeAdapter extends TypeAdapter<String> {

    @Override
    public String read(JsonReader in) throws IOException {
        switch (in.peek()) {
            case STRING:
            case NUMBER:
                return in.nextString();
            case BOOLEAN:
                // 对于布尔类型比较特殊，需要做针对性处理
                return Boolean.toString(in.nextBoolean());
            case NULL:
                in.nextNull();
                return null;
            default:
                in.skipValue();
                return null;
        }
    }

    @Override
    public void write(JsonWriter out, String value) throws IOException {
        out.value(value);
    }
}
