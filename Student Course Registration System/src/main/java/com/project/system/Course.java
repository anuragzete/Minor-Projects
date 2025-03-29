package com.project.system;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Course} class represents a course in the Student-Course Registration System.
 * It contains the course name, description, and a list of enrolled students.
 */
public class Course {

    /** The name of the course (final and immutable). */
    private final String NAME;

    /** A brief description of the course. */
    private String description;

    /** A list of students enrolled in the course. */
    private List<String> enrolledStudents;

    /**
     * Constructs a new {@code Course} instance with a name and description.
     * Initializes an empty list of enrolled students.
     *
     * @param NAME        The name of the course.
     * @param description The description of the course.
     */
    public Course(String NAME, String description) {
        this.NAME = NAME;
        this.description = description;
        this.enrolledStudents = new ArrayList<>();  // Initialize empty list
    }

    /**
     * Gets the name of the course.
     *
     * @return The name of the course.
     */
    protected String getName() {
        return NAME;
    }

    /**
     * Gets the description of the course.
     *
     * @return The course description.
     */
    protected String getDescription() {
        return description;
    }

    /**
     * Sets the description of the course.
     *
     * @param description The new description to set.
     */
    protected void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the list of students enrolled in the course.
     *
     * @return A list of enrolled students.
     */
    protected List<String> getEnrolledStudents() {
        return enrolledStudents;
    }

    /**
     * Enrolls a new student into the course.
     *
     * @param studentName The name of the student to enroll.
     */
    protected void enrollStudent(String studentName) {
        enrolledStudents.add(studentName);
    }

    /**
     * Sets the list of enrolled students for the course.
     *
     * @param students The list of students to be set.
     */
    protected void setEnrolledStudents(List<String> students) {
        this.enrolledStudents = students;
    }
}
