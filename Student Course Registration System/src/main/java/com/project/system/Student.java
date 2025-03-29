package com.project.system;

/**
 * The {@code Student} class represents a student in the Student-Course Registration System.
 * It contains the student's name, email, and the course they are enrolled in.
 */
public class Student {

    /** The name of the student. */
    private String name;

    /** The email of the student. */
    private String email;

    /** The course in which the student is enrolled. */
    private String course;

    /**
     * Constructs a new {@code Student} instance with the specified name, email, and course.
     *
     * @param name   The name of the student.
     * @param email  The email address of the student.
     * @param course The course the student is enrolled in.
     */
    public Student(String name, String email, String course) {
        this.name = name;
        this.email = email;
        this.course = course;
    }

    /**
     * Gets the name of the student.
     *
     * @return The student's name.
     */
    protected String getName() {
        return name;
    }

    /**
     * Sets the name of the student.
     *
     * @param name The new name to set.
     */
    protected void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email of the student.
     *
     * @return The student's email address.
     */
    protected String getEmail() {
        return email;
    }

    /**
     * Sets the email of the student.
     *
     * @param email The new email address to set.
     */
    protected void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the course the student is enrolled in.
     *
     * @return The name of the course.
     */
    protected String getCourse() {
        return course;
    }

    /**
     * Sets the course the student is enrolled in.
     *
     * @param course The new course name to set.
     */
    protected void setCourse(String course) {
        this.course = course;
    }
}
