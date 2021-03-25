package com.team14.cms;

import java.util.ArrayList;
import java.util.List;

public class Professor extends User {
    List<Course> courses;

    public Professor() {
        courses = new ArrayList<>();
    }

    public Professor(Integer id, String fName, String lName, String password){
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.isLoggedIn = false;

        courses = new ArrayList<>();
    }

    public void createCourseDeliverable(Course course, String name, int dueDate) {

    }

    public void deleteCourseDeliverable(Course course, CourseDeliverable deliverable) {

    }

    public void submitIndividualGrade(Student student) {

    }

    public void submitFinalGrades() {

    }

    @Override
    public void update() {

    }
}
