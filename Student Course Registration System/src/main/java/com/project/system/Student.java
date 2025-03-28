package com.project.system;

public class Student {
    private String name;
    private String email;
    private String course;

    public Student( String name, String email, String course) {
        this.name = name;
        this.email = email;
        this.course = course;
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected String getEmail() {
        return email;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    protected String getCourse() {
        return course;
    }

    protected void setCourse(String course) {
        this.course = course;
    }
}
