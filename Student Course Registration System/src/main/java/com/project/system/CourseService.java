package com.project.system;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONObject;

import java.util.List;

public class CourseService {

    private final MongoCollection<Document> courseCollection;
    private final MongoCollection<Document> studentCollection;

    public CourseService(MongoDatabase database) {
        courseCollection = database.getCollection("courses");
        studentCollection = database.getCollection("students");
    }

    protected void addCourse(Course course) {
        Document doc = new Document("name", course.getName())
                .append("description", course.getDescription());
        courseCollection.insertOne(doc);
        System.out.println("Course added: " + course.getName());
    }

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


    protected void searchCourse(String name) {
        Document query = new Document("name", new Document("$regex", name).append("$options", "i"));
        for (Document doc : courseCollection.find(query)) {
            JSONObject json = new JSONObject(doc.toJson());
            System.out.println(json.toString(4));
        }
    }
}
