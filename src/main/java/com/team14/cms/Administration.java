package com.team14.cms;

import java.util.ArrayList;
import java.util.List;

public class Administration extends User {
    List<Student> studentList;
    List<Professor> professorList;
    List<Course> courseList;

    public Administration() {
        studentList = new ArrayList<>();
        professorList = new ArrayList<>();
        courseList = new ArrayList<>();
    }

    public Administration(Integer id, String fName, String lName, String password){
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.isLoggedIn = false;

        studentList = new ArrayList<>();
        professorList = new ArrayList<>();
        courseList = new ArrayList<>();
    }

    public void createCourse(String name, String crn, float creditValue) {

    }

    public void deleteCourse(Course course) {

    }

    public void createProf() {

    }

    public void deleteProf() {

    }

    public void createStudent() {

    }

    public void deleteStudent() {

    }

    @Override
    public void update() {

    }
}
