package com.team14.cms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course extends Subject {


    public Integer id;
    public String name;
    public double creditValue;
    List<Course> prerequisites;

    public  Professor prof;             // prof assigned to the course
    public Map<Student, Float> classList = new HashMap<>();    // students registered in the course

    public boolean isOpen;            // open for registration
    public List<Student> waitlist = new ArrayList<>();

    public Map<CourseDeliverable, Map<Student, Float>> courseDeliverables = new HashMap<>();
    public List<CourseDeliverable> mycourseList=new ArrayList<>();
    private final int maxWaitlist = 10;
    public void addCourseDeliverable(CourseDeliverable c)
    {
        this.mycourseList.add(c);
    }
    public Course() {

    }
    public Course(String name,Integer id, double creditValue, boolean isOpen)
    {
        this.name=name;
        this.id=id;
        this.isOpen=isOpen;
    }
    public Course(String name, Integer id, double creditValue, List<Course> prerequisites, boolean isOpen) {
        this.name = name;
        this.id = id;
        this.creditValue = creditValue;
        this.prerequisites = prerequisites;
        this.isOpen = isOpen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Professor getProf() {
        return prof;
    }

    /* Sets prof and adds them to the observers */
    public void setProf(Professor prof) {
        // if prof is being replaced, remove current prof from observers
        if (this.prof != null) {
            detach(this.prof);
        }
        this.prof = prof;
        attach(this.prof);
    }

    public List<Student> getClassList() {
        return new ArrayList<>(classList.keySet());
    }

    public Student getStudentByID(int id) {
        for (Student s : classList.keySet()) {
            if (s.getId() == id) {
                return s;
            }
        }

        return null;
    }

    public float getGrade(Student student) {
        return classList.get(student);
    }

    /* Adds a student to the classList and adds them to the list of observers */
    public void addToCourse(Student student) {
        classList.putIfAbsent(student, null); // a new student won't have a grade yet
        attach(student);
    }

    public boolean isWaitlistFull() {
        return (waitlist.size() == maxWaitlist);
    }

    public void addToWaitlist(Student student) {
        waitlist.add(student);
    }

    public void removeStudent(Student student) {
        if (!classList.isEmpty() || classList.containsKey(student)) {
            classList.remove(student);
            notifyObservers();
        }
    }

    public void studentSubmitCD(CourseDeliverable deliverable, Student student) {
        courseDeliverables.get(deliverable).put(student, null);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
