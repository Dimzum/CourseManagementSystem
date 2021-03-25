package com.team14.cms;

import java.util.List;

public class Administration extends User {
    List<User> studentList;
    List<User> professorList;
    List<Course> courseList;

    public Administration(Integer id, String fname, String lname, String password){
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.loggedin = false;
    }

    public void createCourse(String name, String crn, float creditValue) {

    }

    public void deleteCourse(Course course) {

    }

    /*public void createProf() {

    }

    public void deleteProf() {

    }
    Moved to ProfessorDao.java
    */

    /*public void createStudent() {

    }

    public void deleteStudent() {

    }
    Moved to StudentDao.java
    */

    @Override
    public void update() {

    }
}
