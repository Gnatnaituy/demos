package com.hasaker.demo.utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MongoDBUtils {

    public static MongoClient initMongo() throws IOException {
        InputStream inputStream = MongoDBUtils.class.getClass()
                .getResourceAsStream("classpath:mongo-config.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        WriteConcern writeConcern = new WriteConcern(Integer.valueOf(properties.getProperty("write")),
                Integer.valueOf(properties.getProperty("writeTimeout")));
        writeConcern.withJournal(Boolean.valueOf(properties.getProperty("journal")));

        MongoClientOptions.Builder builder = MongoClientOptions.builder()
                .connectionsPerHost(Integer.valueOf(properties.getProperty("connectionsPerHost")))
                .connectTimeout(Integer.valueOf(properties.getProperty("connectionTimeout")))
                .cursorFinalizerEnabled(Boolean.valueOf(properties.getProperty("cursorFinalizerEnabled")))
                .maxWaitTime(Integer.valueOf(properties.getProperty("maxWaitTime")))
                .threadsAllowedToBlockForConnectionMultiplier(Integer.valueOf(
                        properties.getProperty("threadsAllowedToBlockForConnectionMultiplier")))
                .socketTimeout(Integer.valueOf(properties.getProperty("socketTimeout")))
                .writeConcern(writeConcern);

        String[] address = properties.getProperty("hostConfString").split(":");
        ServerAddress serverAddress = new ServerAddress(address[0], Integer.valueOf(address[1]));

        return new MongoClient(serverAddress, builder.build());
    }
}
