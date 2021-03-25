package com.team14.cms;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private List<Course> courses;

    public Student(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;

        courses = new ArrayList<>();
    }

    public void registerInCourse(Course course) {

    }

    public void dropCourse(Course course) {

    }

    public void submitCourseDeliverable(CourseDeliverable courseDeliverable) {

    }

    @Override
    public void update() {

    }
}
