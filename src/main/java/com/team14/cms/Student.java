package com.team14.cms;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    public String birthday;

    private List<Course> coursesTaken = new ArrayList<>();

    public Student(int id, String fName, String lName, String password, String birthday) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.isLoggedIn = false;

        this.birthday = birthday;
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
