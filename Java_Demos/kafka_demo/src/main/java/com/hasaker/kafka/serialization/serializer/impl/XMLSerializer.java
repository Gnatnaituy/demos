package com.hasaker.kafka.serialization.serializer.impl;

import com.hasaker.kafka.serialization.serializer.ISerializer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @author 余天堂
 * @since 2019/11/3 15:04
 * @description 大部分情况下可被Json取代
 */
public class XMLSerializer implements ISerializer {

    private static final XStream xStream = new XStream(new DomDriver());

    @Override
    public <T> byte[] serialize(T obj) {

        return xStream.toXML(obj).getBytes();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        String xml = new String(data);

        return (T) xStream.fromXML(xml);
    }
}
