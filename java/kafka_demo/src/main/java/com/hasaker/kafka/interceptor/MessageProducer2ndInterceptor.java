package com.hasaker.kafka.interceptor;

import com.hasaker.kafka.entity.Message;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @author 余天堂
 * @since 2019/10/30 23:59
 * 定义多个拦截器形成拦截链
 */
public class MessageProducer2ndInterceptor implements ProducerInterceptor<String, Message> {
    private volatile long sendSuccess = 0;
    private volatile long sendFailure = 0;

    @Override
    public ProducerRecord<String, Message> onSend(ProducerRecord<String, Message> record) {
        Message modifiedMessage = Message.builder()
                .title("modified twice: " + record.value().getTitle())
                .content("modified twice: " + record.value().getContent())
                .build();

        return new ProducerRecord<>(
                record.topic(),
                record.partition(),
                record.timestamp(),
                record.key(),
                modifiedMessage,
                record.headers());
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception e) {
        if (null != e) {
            sendFailure++;
        } else {
            sendSuccess++;
        }
    }

    @Override
    public void close() {
        double successRatio = (double) sendSuccess / (sendFailure + sendSuccess);
        System.out.println("[INFO] 发送成功率: " + String.format("%f", successRatio * 100) + "%");
    }

    @Override
    public void configure(Map<String, ?> configs) {}
}
