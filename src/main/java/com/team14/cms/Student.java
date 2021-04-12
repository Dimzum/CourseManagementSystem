package com.team14.cms;

import java.util.ArrayList;
import java.util.List;

public class Student extends User implements UserPart {
    @Override
    public void accept(UserPartVisitor v) {
        v.visit(this);
    }

    public enum YearStanding { One, Two, Three, Four }

    public int yearStanding;
    public String birthday;

    public List<Course> coursesTaken = new ArrayList<>();

    public boolean isOnDeansList = false;

    public Student(int id, String fName, String lName, String password, String birthday) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.isLoggedIn = false;

        this.birthday = birthday;
    }

    // Doesn't actually register student in course
    // Only adds them to the waitlist until admin accepts the request
    public boolean registerInCourse(Course course) {
        course.addToWaitlist(this);
        if(course.id==10003&&!isPreLearned(10002)||course.id==10002&&!isPreLearned(10001)) return false;
        if(!this.coursesTaken.contains(course))this.coursesTaken.add(course);
        return true;
    }

    public void dropCourse(Course course) {
        course.removeStudent(this);
    }

    public void submitCourseDeliverable(Course course, CourseDeliverable courseDeliverable) {
        course.studentSubmitCD(courseDeliverable, this);
    }

    public float getAvg() {

        return 0;
    }

    @Override
    public void update() {

    }
    public boolean isPreLearned(int courseId)
    {
        for(Course c:coursesTaken)
        {
            if(c.id==courseId) return true;
        }
        return false;
    }
}
