package com.project.system;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * The {@code MongoDBUtil} class provides utility methods to start an embedded MongoDB server,
 * establish a connection, and access the MongoDB database.
 */
public class MongoDBUtil {

    /** The executable for the embedded MongoDB instance. */
    private static MongodExecutable mongodExecutable;

    /** The MongoDB client used to connect to the database. */
    private static MongoClient mongoClient;

    /**
     * Starts the embedded MongoDB server and establishes a connection to the MongoDB instance.
     *
     * @return The {@link MongoDatabase} instance connected to the "student_course_db" database.
     * @throws RuntimeException if MongoDB fails to start.
     */
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

    /**
     * Closes the MongoDB client connection and stops the embedded MongoDB server.
     */
    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
        if (mongodExecutable != null) {
            mongodExecutable.stop();
        }
    }
}
