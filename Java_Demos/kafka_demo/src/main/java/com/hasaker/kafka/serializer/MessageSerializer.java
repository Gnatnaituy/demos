package com.hasaker.kafka.serializer;

import com.hasaker.kafka.entity.Message;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author 余天堂
 * @since 2019/10/30 23:14
 * 自定义序列化器MessageSerializer
 */
public class MessageSerializer implements Serializer<Message> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}

    @Override
    public byte[] serialize(String topic, Message data) {
        if (data == null) {
            return null;
        }

        byte[] title, content;
        try {
            title = data.getTitle() == null ? new byte[0] : data.getTitle().getBytes(StandardCharsets.UTF_8);
            content = data.getContent() == null ? new byte[0] : data.getContent().getBytes(StandardCharsets.UTF_8);
            ByteBuffer buffer = ByteBuffer.allocate(title.length + content.length);
            buffer.put(title);
            buffer.put(content);

            return buffer.array();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new byte[0];
    }

    @Override
    public void close() {}
}
