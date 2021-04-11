package com.team14.cms;

import java.util.ArrayList;
import java.util.List;

public class Administration extends User implements UserPart {
    List<Student> studentList = new ArrayList<>();
    List<Professor> professorList = new ArrayList<>();

    public Administration(Integer id, String fName, String lName, String password) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.isLoggedIn = false;
    }

    public void createCourse(String name, Integer id, double creditValue, boolean isOpen) {
        Course c = new Course(name, id, creditValue, isOpen);
        courses.add(c);
    }

    public void deleteCourse(int id) {
        if (!courses.isEmpty()) {
            courses.removeIf(course -> (course.getId() == id));
        }
    }

    public void createProf(int id, String fName, String lName, String password) {
        Professor p = new Professor(id, fName, lName, password);
        professorList.add(p);
    }

    public void deleteProf(int id) {
        if (!professorList.isEmpty()) {
            professorList.removeIf(professor -> (professor.getId() == id));
        }
    }

    public void setProfForCourse(Course course, Professor professor) {
        course.setProf(professor);
    }

    public void createStudent(int id, String fName, String lName, String password, String birthday) {
        Student s = new Student(id, fName, lName, password, birthday);
        studentList.add(s);
    }

    public void deleteStudent(int id) {
        if (!studentList.isEmpty()) {
            studentList.removeIf(student -> (student.getId() == id));
        }
    }

    public void registerStudentInCourse(Course course) {
        course.addToCourse(course.waitlist.remove(0));
    }

    @Override
    public void update() {

    }

    @Override
    public void accept(UserPartVisitor v) {
        v.visit(this);
    }
}

