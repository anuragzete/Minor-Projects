package com.project.system;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private final String NAME;
    private String description;
    private List<String> enrolledStudents;

    public Course(String NAME, String description) {
        this.NAME = NAME;
        this.description = description;
        this.enrolledStudents = new ArrayList<>();  // Initialize empty list
    }

    protected String getName() {
        return NAME;
    }

    protected String getDescription() {
        return description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    protected List<String> getEnrolledStudents() {
        return enrolledStudents;
    }

    protected void enrollStudent(String studentName) {
        enrolledStudents.add(studentName);
    }

    protected void setEnrolledStudents(List<String> students) {
        this.enrolledStudents = students;
    }
}
