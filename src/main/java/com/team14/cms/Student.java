package com.team14.cms;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Student extends User implements UserPart {
    @Autowired
    CourseDao courseDao;

    @Override
    public void accept(UserPartVisitor v) {
        v.visit(this);
    }

    public enum YearStanding { One, Two, Three, Four }

    public int yearStanding;
    public String birthday;

    public List<Course> coursesTaken = new ArrayList<>();
    public Collection<Integer> Taken = null;

    public boolean isOnDeansList = false;

    public boolean isHandIn = false;

    public Student(int id, String fName, String lName, String password, String birthday) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.isLoggedIn = false;

        this.birthday = birthday;
    }

    public boolean registerInCourse(Course c) {
        if (coursesTaken.contains(c)){
            return true;
        }
        if (c.prerequisites.size() == 0){
            this.coursesTaken.add(c);
            c.addToCourse(this);
            return true;
        }
        /*if (Taken == null){
            return false;
        }*/
        for (Course course : c.prerequisites){
            if (Taken.contains(course.getId())){
                System.out.println(course.getId());
                continue;
            }else{
                return false;
            }
        }

        this.coursesTaken.add(c);
        c.addToCourse(this);
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
    public boolean isPreLearned(int courseId)
    {
        for(Course c:coursesTaken)
        {
            if(c.id==courseId) return true;
        }
        return false;
    }

    @Override
    public void update() {

    }
}
