package com.hasaker.kafka.serialization.serializer.impl;

import com.alibaba.fastjson.JSONObject;
import com.hasaker.kafka.serialization.serializer.ISerializer;

/**
 * @author 余天堂
 * @since 2019/11/3 14:48
 * @description 跨语言，可读性好，效率相对没那么高，这里用FastJson实现，可以完成很多定制
 */
public class JSONSerializer implements ISerializer {

    @Override
    public <T> byte[] serialize(T obj) {
        try {
            String json = JSONObject.toJSONString(obj);

            return json.getBytes();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        String json = new String(data);

        try {
            return JSONObject.parseObject(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
