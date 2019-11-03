package com.hasaker.kafka.serialization.serializer.impl;

import com.hasaker.kafka.serialization.serializer.ISerializer;
import org.apache.thrift.TBase;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TBinaryProtocol;

/**
 * @author 余天堂
 * @since 2019/11/3 16:55
 * @description 需要借助IDL
 */
public class ThriftSerializer implements ISerializer {
    /**
     * 序列化对象
     *
     * @param object 对象
     * @return 字节数组
     */
    @Override
    public <T> byte[] serialize(T object) {
        try {
            TSerializer serializer = new TSerializer(new TBinaryProtocol.Factory());

            return serializer.serialize((TBase) object);
        } catch (TException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 反序列化对象
     *
     * @param data  序列化字节数组
     * @param clazz 原始类型的类对象
     * @return 对象
     */
    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        try {
            TBase tBase = (TBase) clazz.newInstance();
            TDeserializer deserializer = new TDeserializer();
            deserializer.deserialize(tBase, data);

            return (T) tBase;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
