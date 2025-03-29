package com.project.system;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * The {@code StudentService} class provides CRUD operations for managing students in the MongoDB database.
 * It handles adding, updating, deleting, and searching student records.
 */
public class StudentService {

    /** The MongoDB collection for storing student information. */
    private final MongoCollection<Document> studentCollection;

    /**
     * Constructs a new {@code StudentService} instance with the specified MongoDB database.
     *
     * @param database The MongoDB database used for storing student data.
     */
    public StudentService(MongoDatabase database) {
        studentCollection = database.getCollection("students");
    }

    /**
     * Adds a new student to the MongoDB collection.
     * <p>
     * Also updates the course document to include the student in its enrolled list.
     * </p>
     *
     * @param student The {@link Student} object representing the student to add.
     */
    protected void addStudent(Student student) {
        MongoDatabase database = MongoDBUtil.getDatabase();
        MongoCollection<Document> courseCollection = database.getCollection("courses");

        Document courseQuery = new Document("name", student.getCourse());
        long courseCount = courseCollection.countDocuments(courseQuery);

        if (courseCount == 0) {
            System.out.println("Course '" + student.getCourse() + "' does not exist. Please add the course first.");
            return;
        }

        Document doc = new Document("name", student.getName())
                .append("email", student.getEmail())
                .append("course", student.getCourse());

        studentCollection.insertOne(doc);

        String studentName = student.getName();
        courseCollection.updateOne(
                courseQuery,
                new Document("$addToSet", new Document("students", studentName))
        );
        System.out.println("Student added: " + student.getName() + ", ID: " + doc.getObjectId("_id"));
    }

    /**
     * Displays all students stored in the MongoDB collection.
     * <p>
     * Prints the ID, name, email, and course of each student.
     * </p>
     */
    protected void displayAllStudents() {
        System.out.println("\nAll Students:\n");

        FindIterable<Document> students = studentCollection.find();
        boolean found = false;

        for (Document doc : students) {
            found = true;

            String id = doc.getObjectId("_id").toHexString();
            String name = doc.getString("name");
            String email = doc.getString("email");
            String course = doc.getString("course");

            // Display student details
            System.out.println("ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
            System.out.println("Course: " + (course != null ? course : "Unassigned"));
            System.out.println("-------------------------------------------------");
        }

        if (!found) {
            System.out.println("No students found in the system.");
        }
    }

    /**
     * Updates the details of an existing student by ID.
     * <p>
     * If the student is moved to a new course, it removes them from the old course's list
     * and adds them to the new course's list.
     * </p>
     *
     * @param id The MongoDB ID of the student to update.
     * @param name The new name of the student (if not empty).
     * @param email The new email of the student (if not empty).
     * @param course The new course of the student (if not empty).
     */
    protected void updateStudent(String id, String name, String email, String course) {
        MongoDatabase database = MongoDBUtil.getDatabase();
        ObjectId objectId = new ObjectId(id);
        Document query = new Document("_id", objectId);

        Document existingStudent = studentCollection.find(query).first();

        if (existingStudent == null) {
            System.out.println("No student found with ID: " + id);
            return;
        }

        String oldCourse = existingStudent.getString("course");

        Document updateFields = new Document();
        if (!name.isEmpty()) updateFields.append("name", name);
        if (!email.isEmpty()) updateFields.append("email", email);

        if (!course.isEmpty()) {
            MongoCollection<Document> courseCollection = database.getCollection("courses");

            Document courseQuery = new Document("name", course);
            if (courseCollection.find(courseQuery).first() == null) {
                System.out.println("Course '" + course + "' does not exist. Please add the course first.");
                return;
            }

            updateFields.append("course", course);

            if (!oldCourse.equals(course)) {
                courseCollection.updateOne(
                        new Document("name", oldCourse),
                        new Document("$pull", new Document("students", existingStudent.getString("name")))
                );

                courseCollection.updateOne(
                        courseQuery,
                        new Document("$addToSet", new Document("students", existingStudent.getString("name")))
                );
            }
        }

        if (!updateFields.isEmpty()) {
            Document update = new Document("$set", updateFields);
            studentCollection.updateOne(query, update);
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("No fields to update.");
        }
    }

    /**
     * Deletes a student by their ID from the MongoDB collection.
     * <p>
     * Also removes the student from the course they were enrolled in.
     * </p>
     *
     * @param id The MongoDB ID of the student to delete.
     */
    protected void deleteStudent(String id) {
        MongoDatabase database = MongoDBUtil.getDatabase();
        ObjectId objectId = new ObjectId(id);
        Document query = new Document("_id", objectId);

        Document student = studentCollection.find(query).first();

        if (student == null) {
            System.out.println("No student found with ID: " + id);
            return;
        }

        String studentName = student.getString("name");
        String courseName = student.getString("course");

        MongoCollection<Document> courseCollection = database.getCollection("courses");
        courseCollection.updateOne(
                new Document("name", courseName),
                new Document("$pull", new Document("students", studentName))
        );

        studentCollection.deleteOne(query);
        System.out.println("Student '" + studentName + "' deleted successfully!");
    }

    /**
     * Searches for a student by their ID in the MongoDB collection.
     * <p>
     * Prints the student details if found; otherwise, displays an appropriate message.
     * </p>
     *
     * @param id The MongoDB ID of the student to search for.
     */
    protected void searchStudent(String id) {
        try {
            ObjectId objectId = new ObjectId(id);

            Document query = new Document("_id", objectId);
            Document student = studentCollection.find(query).first();

            if (student != null) {
                System.out.println("\nStudent Found:\n");

                String name = student.getString("name");
                String email = student.getString("email");
                String course = student.getString("course");

                // Display student details
                System.out.println("ID: " + student.getObjectId("_id").toHexString());
                System.out.println("Name: " + name);
                System.out.println("Email: " + email);
                System.out.println("Course: " + (course != null ? course : "Unassigned"));
                System.out.println("-------------------------------------------------");
            } else {
                System.out.println("No student found with ID: " + id);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid ID format. Please enter a valid MongoDB ID.");
        }
    }
}
