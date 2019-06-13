package com.hasaker.demo;

import com.hasaker.demo.utils.MongoDBUtils;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.concurrent.ConcurrentHashMap;

public class MongoDemo {

    public static void testMongo() {
        try {
            MongoClient mongoClient = MongoDBUtils.initMongo();
            MongoDatabase mongoDatabase = mongoClient.getDatabase("demo");
            MongoCollection mongoCollection = mongoDatabase.getCollection("user");
            mongoCollection.insertOne(new Document()
                    .append("username", "吴彦祖")
                    .append("password", "1233122")
                    .append("age", 42));
            System.out.println("操作成功");

            StringBuilder sb = new StringBuilder();
            ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
