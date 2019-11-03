package com.hasaker.kafka.serialization;

import com.hasaker.kafka.serialization.serializer.ISerializer;
import com.hasaker.kafka.serialization.serializer.impl.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 余天堂
 * @since 2019/11/3 14:04
 * @description 序列化引擎
 */
public class SerializerEngine {

    private static final Map<SerializeTypeEnum, ISerializer> SERIALIZER_MAP = new ConcurrentHashMap<>();

    static {
        SERIALIZER_MAP.put(SerializeTypeEnum.DefaultJavaSerializer, new DefaultJavaSerializer());
        SERIALIZER_MAP.put(SerializeTypeEnum.HessianSerializer, new HessianSerializer());
        SERIALIZER_MAP.put(SerializeTypeEnum.XMLSerializer, new XMLSerializer());
        SERIALIZER_MAP.put(SerializeTypeEnum.JOSNSerializer, new JSONSerializer());
        SERIALIZER_MAP.put(SerializeTypeEnum.ProtoStuffSerializer, new ProtoStuffSerializer());
        SERIALIZER_MAP.put(SerializeTypeEnum.AvroSerializer, new AvroSerializer());
        SERIALIZER_MAP.put(SerializeTypeEnum.ThriftSerializer, new ThriftSerializer());
    }

    public static <T> byte[] serialize(T object, SerializeTypeEnum serializeTypeEnum) {
        ISerializer serializer = SERIALIZER_MAP.get(serializeTypeEnum);

        return serializer.serialize(object);
    }

    public static <T> T deserialze(byte[] data, Class<T> clazz, SerializeTypeEnum serializeTypeEnum) {
        ISerializer serializer = SERIALIZER_MAP.get(serializeTypeEnum);

        return serializer.deserialize(data, clazz);
    }
}
