package com.project.system;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtil {

    private static MongodExecutable mongodExecutable;
    private static MongoClient mongoClient;

    public static MongoDatabase getDatabase() {
        try {
            // Start embedded MongoDB
            MongodStarter starter = MongodStarter.getDefaultInstance();

            MongodConfig mongodConfig = MongodConfig.builder()
                    .version(Version.Main.V6_0)  // MongoDB Version
                    .net(new de.flapdoodle.embed.mongo.config.Net("localhost", 27017, Network.localhostIsIPv6()))
                    .build();

            mongodExecutable = starter.prepare(mongodConfig);
            mongodExecutable.start();

            // Connect to MongoDB
            mongoClient = MongoClients.create("mongodb://localhost:27017");
            return mongoClient.getDatabase("student_course_db");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to start MongoDB.");
        }
    }

    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
        if (mongodExecutable != null) {
            mongodExecutable.stop();
        }
    }
}
