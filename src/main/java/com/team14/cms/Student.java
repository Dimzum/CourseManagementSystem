package com.team14.cms;

import java.util.List;

public class Student extends User {
    private List<Course> courses;
    private List<Course> taken;
    public String birthday;

    public Student(Integer id, String fname, String lname, String password, String birthday) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.birthday = birthday;
        this.loggedin = false;
    }

    public void registerInCourse(Course course) {

    }

    public void dropCourse(Course course) {

    }

    public void submitCourseDeliverable(Deliverable courseDeliverable) {

    }

    @Override
    public void update() {

    }
}
