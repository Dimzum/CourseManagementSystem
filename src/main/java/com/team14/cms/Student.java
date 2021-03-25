package com.team14.cms;

import java.util.ArrayList;
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
<<<<<<< Updated upstream

        courses = new ArrayList<>();
=======
        this.birthday = birthday;
        this.loggedin = false;
>>>>>>> Stashed changes
    }

    public void registerInCourse(Course course) {

    }

    public void dropCourse(Course course) {

    }

    public List<Course> getTaken() {
        return taken;
    }

    public void submitCourseDeliverable(CourseDeliverable courseDeliverable) {

    }

    @Override
    public void update() {

    }
}
