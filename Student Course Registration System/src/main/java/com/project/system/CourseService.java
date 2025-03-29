package com.project.system;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONObject;

import java.util.List;

/**
 * The {@code CourseService} class handles CRUD operations related to courses in the MongoDB database.
 * It allows adding, deleting, searching, and displaying course information.
 */
public class CourseService {

    /** The MongoDB collection for storing course information. */
    private final MongoCollection<Document> courseCollection;

    /** The MongoDB collection for storing student information. */
    private final MongoCollection<Document> studentCollection;

    /**
     * Constructs a new {@code CourseService} instance using the provided MongoDB database connection.
     *
     * @param database The MongoDB database to interact with.
     */
    public CourseService(MongoDatabase database) {
        courseCollection = database.getCollection("courses");
        studentCollection = database.getCollection("students");
    }

    /**
     * Adds a new course to the MongoDB collection.
     *
     * @param course The {@link Course} object representing the course details.
     */
    protected void addCourse(Course course) {
        Document doc = new Document("name", course.getName())
                .append("description", course.getDescription());
        courseCollection.insertOne(doc);
        System.out.println("Course added: " + course.getName());
    }

    /**
     * Displays all courses stored in the MongoDB collection.
     * <p>
     * For each course, it prints the course name, description, and the list of enrolled students (if any).
     * </p>
     */
    protected void displayAllCourses() {
        System.out.println("\nAll Courses:\n");

        for (Document doc : courseCollection.find()) {
            String courseName = doc.getString("name");
            String description = doc.getString("description");
            List<String> students = doc.getList("students", String.class);

            // Display Course Info
            System.out.println("Course Name: " + courseName);
            System.out.println("Description: " + description);

            if (students != null && !students.isEmpty()) {
                System.out.println("Enrolled Students: ");
                for (String student : students) {
                    System.out.println("   - " + student);
                }
            } else {
                System.out.println("No students enrolled.");
            }
            System.out.println("-------------------------------------------------");
        }
    }

    /**
     * Deletes a course by its name from the MongoDB collection.
     * <p>
     * Also unenrolls students from the deleted course by removing the course reference from their records.
     * </p>
     *
     * @param name The name of the course to delete.
     */
    protected void deleteCourse(String name) {
        Document query = new Document("name", name);

        Document courseDoc = courseCollection.find(query).first();

        if (courseDoc == null) {
            System.out.println("No course found with the name: " + name);
            return;
        }

        long deletedCount = courseCollection.deleteOne(query).getDeletedCount();

        if (deletedCount > 0) {
            System.out.println("Course '" + name + "' deleted successfully!");

            Document studentQuery = new Document("course", name);
            Document unsetCourse = new Document("$set", new Document("course", ""));

            long updatedCount = studentCollection.updateMany(studentQuery, unsetCourse).getModifiedCount();

            System.out.println(updatedCount + " students were unenrolled from the deleted course.");
        } else {
            System.out.println("No course found with the name: " + name);
        }
    }

    /**
     * Searches for courses by name (case-insensitive) using a regular expression.
     *
     * @param name The name of the course to search for (or a partial name).
     */
    protected void searchCourse(String name) {
        Document query = new Document("name", new Document("$regex", name).append("$options", "i"));
        for (Document doc : courseCollection.find(query)) {
            JSONObject json = new JSONObject(doc.toJson());
            System.out.println(json.toString(4));
        }
    }
}
