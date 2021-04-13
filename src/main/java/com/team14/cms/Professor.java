package com.team14.cms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Professor extends User implements UserPart {

    CourseDeliverableFactory cdFactory = new CourseDeliverableFactory();

    public Professor(Integer id, String fName, String lName, String password){
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.isLoggedIn = false;
    }

    public void createCourseDeliverable(Integer id, CourseDeliverable.DeliverableType type, String name, String dueDate) {
        for (Course c : courses) {
            if (c.getId() == id) {
                c.courseDeliverables.put(cdFactory.createCourseDeliverable(type, name, dueDate), null);
            }
        }
    }

    public void deleteCourseDeliverable(Integer cid, String name) {
        for (Course c : courses) {
            if (c.getId() == cid) {
                for (CourseDeliverable cd : c.courseDeliverables.keySet()) {
                    if (cd.name.equalsIgnoreCase(name)) {
                        c.courseDeliverables.remove(cd);
                    }
                }
            }
        }
    }

    public double submitIndividualGrade(Course course, Student student) {
        return course.classList.get(student);
    }

    public Map<Student, Double> submitFinalGrades(Course course) {
        return course.classList;
    }

    @Override
    public void update(Course.State state) {

    }

    @Override
    public void accept(UserPartVisitor v) {
        v.visit(this);
    }
}
