package com.team14.cms;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    public enum YearStanding { One, Two, Three, Four }

    public int yearStanding;
    public String birthday;

    public List<Course> coursesTaken = new ArrayList<>();

    public boolean isOnDeansList = false;

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

    public float getAvg() {

        return 0;
    }

    @Override
    public void update() {

    }
}
