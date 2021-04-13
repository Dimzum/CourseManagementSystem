package com.team14.cms;

import java.util.*;

public class Course extends Subject {
    public enum State {
        newProf,
        addStudent,
        removeStudent,
        cdSubmission
    };

    public Integer id;
    public String name;
    public double creditValue;
    List<Course> prerequisites = new ArrayList<>();
    List<Course> preclusions = new ArrayList<>();

    public  Professor prof;             // prof assigned to the course
    public Map<Student, Double> classList = new HashMap<>();    // students registered in the course

    public boolean isOpen;            // open for registration
    public List<Student> waitlist = new ArrayList<>();
    public final int maxWaitlist = 10;

    public Map<CourseDeliverable, Map<Student, Double>> courseDeliverables = new HashMap<>();
    public List<CourseDeliverable> mycourseList=new ArrayList<>();
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
    public Course(String name, Integer id, double creditValue, List<Course> prerequisites, List<Course> preclusions, boolean isOpen) {
        this.name = name;
        this.id = id;
        this.creditValue = creditValue;
        this.prerequisites = prerequisites;
        this.preclusions = preclusions;
        this.isOpen = true;
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
        notifyObservers(State.newProf);
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

    public double getGrade(Student student) {
        if (classList != null){
            for (Student stu : classList.keySet()){
                if (stu.getId() == student.getId()){
                    return classList.get(stu);
                }
            }
        }
        return classList.get(student);
    }

    /* Adds a student to the classList and adds them to the list of observers */
    public void addToCourse(Student student) {
        boolean check = true;
        for (Student s : classList.keySet()) {
            if (s.getId() == student.getId()) {
                check = false;
                break;
            }else{
                continue;
            }
        }
        if (check) {
            classList.put(student, null);// a new student won't have a grade yet
            if (courseDeliverables != null){
                for (CourseDeliverable cd : courseDeliverables.keySet()){
                    courseDeliverables.get(cd).put(student, 0.00);
                }
            }
        }
        attach(student);
        notifyObservers(State.addStudent);
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
            notifyObservers(State.removeStudent);
        }
    }

    public void studentSubmitCD(CourseDeliverable deliverable, Student student) {
        courseDeliverables.get(deliverable).put(student, null);
        notifyObservers(State.cdSubmission);
    }

    @Override
    public void notifyObservers(Course.State state) {
        for (Observer o : observers) {
            o.update(state);
        }
    }
}
