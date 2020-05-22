package com.hasaker.kafka.serialization.serializer;

/**
 * @author 余天堂
 * @since 2019/11/3 13:51
 */
public interface ISerializer {

    /**
     * 序列化对象
     * @param object 对象
     * @param <T> 对象类型
     * @return 字节数组
     */
    <T> byte[] serialize(T object);

    /**
     * 反序列化对象
     * @param data 序列化字节数组
     * @param clazz 原始类型的类对象
     * @param <T> 原始类型
     * @return 对象
     */
    <T> T deserialize(byte[] data, Class<T> clazz);
}
