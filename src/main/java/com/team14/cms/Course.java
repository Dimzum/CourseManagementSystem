package com.team14.cms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course extends Subject {
    String name;
    String crn;
    float creditValue;
    List<Course> prerequisites;

    private Professor prof;             // prof assigned to the course
    private Map<Student, Float> classList;    // students registered in the course

    private boolean isOpen;            // open for registration
    private final int maxWaitlist = 10;
    private List<Student> waitlist;

    private Map<CourseDeliverable, Map<Student, Float>> courseDeliverables;

    public Course() {
        classList = new HashMap<>();
        waitlist = new ArrayList<>();
        courseDeliverables = new HashMap<>();
    }

    public Course(String name, String crn, float creditValue, List<Course> prerequisites, boolean isOpen) {
        this.name = name;
        this.crn = crn;
        this.creditValue = creditValue;
        this.prerequisites = prerequisites;
        this.isOpen = isOpen;

        classList = new HashMap<>();
        waitlist = new ArrayList<>();
        courseDeliverables = new HashMap<>();
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

    public Student getStudent(int id) {
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

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
