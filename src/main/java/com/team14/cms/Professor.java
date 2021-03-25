package com.team14.cms;

import java.util.List;

public class Professor extends User {
    List<Course> courses;

    public Professor(Integer id, String fname, String lname, String password){
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.loggedin = false;
    }

    public void createCourseDeliverable(Course course, String name, int dueDate) {

    }

    public void deleteCourseDeliverable(Course course, Deliverable deliverable) {

    }

    public void submitIndividualGrade(Student student) {

    }

    public void submitFinalGrades() {

    }

    @Override
    public void update() {

    }
}
