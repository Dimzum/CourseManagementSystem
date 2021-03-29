package com.team14.cms;

import java.util.ArrayList;
import java.util.List;

public class Professor extends User {

    CourseDeliverableFactory cdFactor = new CourseDeliverableFactory();

    public Professor(Integer id, String fName, String lName, String password){
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.isLoggedIn = false;
    }

    public void createCourseDeliverable(Course course, String name, int dueDate) {

    }

    public void deleteCourseDeliverable(Course course, CourseDeliverable deliverable) {

    }

    public void submitIndividualGrade(Student student) {

    }

    public void submitFinalGrades(Course course) {

    }

    @Override
    public void update() {

    }
	@Override
	public void accept(UserVisitor v) {
        v.visit(this);
    }
}
