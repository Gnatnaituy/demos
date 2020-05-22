package com.hasaker.kafka.producer;

import com.hasaker.kafka.entity.Message;
import com.hasaker.kafka.interceptor.MessageProducer2ndInterceptor;
import com.hasaker.kafka.interceptor.MessageProducerInterceptor;
import com.hasaker.kafka.partitioner.DemoPartitioner;
import com.hasaker.kafka.serializer.MessageSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author 余天堂
 * @since 2019/10/30 23:23
 */
public class MessageProducer {
    public static final String brokers = "localhost:9092";
    public static final String topic = "demo";
    public static final String clientId = "producer.client.id.demo.1";


    public static Properties initConfig() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
        properties.put(ProducerConfig.RETRIES_CONFIG, 5);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, MessageSerializer.class.getName());
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, DemoPartitioner.class.getName());
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, MessageProducerInterceptor.class.getName()
            + "," + MessageProducer2ndInterceptor.class.getName());

        return properties;
    }

    public static void main(String[] args) {
        Message message = Message.builder().title("标题").content("内容").build();

        Properties properties = initConfig();
        KafkaProducer<String, Message> producer = new KafkaProducer<>(properties);
        ProducerRecord<String, Message> record = new ProducerRecord<>(topic, message.toString(), message);

        try {
            producer.send(record, (metadata, exception) -> {
                if (exception != null) {
                    exception.printStackTrace();
                } else {
                    System.out.println("topic:                  " + metadata.topic());
                    System.out.println("partition:              " + metadata.partition());
                    System.out.println("offset:                 " + metadata.offset());
                    System.out.println("timestamp:              " + metadata.timestamp());
                    System.out.println("serializedKeySize:      " + metadata.serializedKeySize());
                    System.out.println("serializedValueSize:    " + metadata.serializedValueSize());
                    System.out.println();
                }
            }).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        producer.close();
    }
}
