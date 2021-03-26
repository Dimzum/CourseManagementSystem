package com.team14.cms;

import java.util.ArrayList;
import java.util.List;

public class Administration extends User {
    List<Student> studentList = new ArrayList<>();
    List<Professor> professorList = new ArrayList<>();

    public Administration(Integer id, String fName, String lName, String password){
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.isLoggedIn = false;
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
