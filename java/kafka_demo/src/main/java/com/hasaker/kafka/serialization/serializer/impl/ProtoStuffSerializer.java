package com.hasaker.kafka.serialization.serializer.impl;

import com.hasaker.kafka.serialization.serializer.ISerializer;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 余天堂
 * @since 2019/11/3 15:07
 * @description 基于protostuff的protocolbuffer序列化工具，各方面效率都高，且不需要借助IDL（接口描述语言）
 */
public class ProtoStuffSerializer implements ISerializer {

    /**
     * 避免每次序列化都重新申请Buffer空间
     */
    private static final LinkedBuffer BUFFER = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);

    /**
     * 用户缓存对象与Schema的对应关系，避免重复创建Schema
     */
    private static final Map<Class<?>, Schema<?>> CACHED_SCHEMA = new ConcurrentHashMap<>();


    /**
     * 序列化对象
     * @param object 对象
     * @return 字节数组
     */
    @Override
    public <T> byte[] serialize(T object) {
        Class<T> clazz = (Class<T>) object.getClass();
        Schema<T> schema = getSchema(clazz);
        byte[] data;

        try {
            data = ProtostuffIOUtil.toByteArray(object, schema, BUFFER);
        } finally {
            BUFFER.clear();
        }

        return data;
    }

    /**
     * 反序列化对象
     * @param data  序列化字节数组
     * @param clazz 原始类型的类对象
     * @return 对象
     */
    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        Schema<T> schema = getSchema(clazz);
        T object = schema.newMessage();

        ProtostuffIOUtil.mergeFrom(data, object, schema);

        return object;
    }

    private static <T> Schema<T> getSchema(Class<T> clazz) {
        Schema<T> schema = (Schema<T>) CACHED_SCHEMA.get(clazz);

        if (Objects.isNull(schema)) {
            // 这个schema通过RuntimeSchema进行懒创建并缓存
            // 所以可以一直调用RuntimeSchema.getSchema(),这个方法是线程安全的
            schema = RuntimeSchema.getSchema(clazz);
            if (Objects.nonNull(schema)) {
                CACHED_SCHEMA.put(clazz, schema);
            }
        }

        return schema;
    }
}
