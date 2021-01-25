package com.lft.kaoqinclient.http.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * author LFT
 *
 * @date 2021/1/18 19:53
 */
public class ListTypeAdapter implements JsonDeserializer<List> {

    @SuppressWarnings("unchecked")
    @Override
    public List deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        // 如果这是一个数组
        if (json.isJsonArray()) {
            JsonArray array = json.getAsJsonArray();
            // 获取 List 上的泛型
            Type itemType = ((ParameterizedType) typeOfT).getActualTypeArguments()[0];
            List list = new ArrayList();
            for (int i = 0; i < array.size(); i++) {
                JsonElement element = array.get(i);
                // 解析 List 中的条目对象
                Object item = context.deserialize(element, itemType);
                list.add(item);
            }
            return list;
        } else {
            // 类型不符，直接返回 null
            return null;
        }
    }
}
