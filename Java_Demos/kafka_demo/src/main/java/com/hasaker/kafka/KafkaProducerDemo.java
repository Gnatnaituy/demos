package com.hasaker.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author 余天堂
 * @since 2019/10/30 21:45
 */
public class KafkaProducerDemo {
    public static final String brokers = "localhost:9092";
    public static final String topic = "demo";
    public static final String clientId = "producer.client.id.demo.1";


    public static Properties initConfig() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
        properties.put(ProducerConfig.RETRIES_CONFIG, 5);

        return properties;
    }

    public static void main(String[] args) {
        Properties properties = initConfig();
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "第一条Kafka消息！");

        try {
            // 同步发送
//            producer.send(record).get();

            // 异步发送
//            Future<RecordMetadata> future = producer.send(record);
//            RecordMetadata metadata = future.get();
//            System.out.println("topic:                  " + metadata.topic());
//            System.out.println("partition:              " + metadata.partition());
//            System.out.println("offset:                 " + metadata.offset());
//            System.out.println("timestamp:              " + metadata.timestamp());
//            System.out.println("serializedKeySize:      " + metadata.serializedKeySize());
//            System.out.println("serializedValueSize:    " + metadata.serializedValueSize());
//            System.out.println();

            // 异步发送 + 回调函数
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
    }
}
