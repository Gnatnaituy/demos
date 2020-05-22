package com.hasaker.kafka.serialization.serializer.impl;

import com.hasaker.kafka.serialization.serializer.ISerializer;
import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author 余天堂
 * @since 2019/11/3 16:09
 * @description 需要借助IDL
 */
public class AvroSerializer implements ISerializer {
    /**
     * 序列化对象
     *
     * @param object 对象
     * @return 字节数组
     */
    @Override
    public <T> byte[] serialize(T object) {
        try {
            DatumWriter<T> userDatumWriter = new SpecificDatumWriter(object.getClass());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BinaryEncoder binaryEncoder = EncoderFactory.get().directBinaryEncoder(byteArrayOutputStream, null);

            userDatumWriter.write(object, binaryEncoder);

            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
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
            DatumReader<T> userDatumReader = new SpecificDatumReader(clazz);
            BinaryDecoder binaryDecoder = DecoderFactory.get().directBinaryDecoder(new ByteArrayInputStream(data), null);

            return userDatumReader.read(clazz.newInstance(), binaryDecoder);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
