package com.hasaker.kafka.serialization.serializer.impl;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.hasaker.kafka.serialization.serializer.ISerializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author 余天堂
 * @since 2019/11/3 14:46
 * @description 使用方法和DefaultJavaSerializer类似，但效率高，而且跨语言，
 *              在大部分场景下可取代DefaultJavaSerializer（在某些情况下支持没有Java原生的好）
 */
public class HessianSerializer implements ISerializer {

    @Override
    public byte[] serialize(Object obj) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            HessianOutput hessianOutput = new HessianOutput(byteArrayOutputStream);
            hessianOutput.writeObject(obj);

            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data)) {
            HessianInput hessianInput = new HessianInput(byteArrayInputStream);

            return (T) hessianInput.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
