package com.team14.cms;

public abstract class CourseDeliverable {
    public enum DeliverableType {
        Assignment,
        Test,
        Exam
    }

    public String name;
    public String deadline;

    protected int grade;

    public CourseDeliverable() {

    }

    public int getGrade() {
        return grade;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }

    public abstract DeliverableType getType();
}

class Assignment extends CourseDeliverable {
    public Assignment(String name, String deadline) {
        this.name = name;
        this.deadline = deadline;
    }

    @Override
    public DeliverableType getType() {
        return DeliverableType.Assignment;
    }
}

class Test extends CourseDeliverable {
    public Test(String name, String deadline) {
        this.name = name;
        this.deadline = deadline;
    }

    @Override
    public DeliverableType getType() {
        return DeliverableType.Test;
    }
}

class Exam extends CourseDeliverable {
    public Exam(String name, String deadline) {
        this.name = name;
        this.deadline = deadline;
    }

    @Override
    public DeliverableType getType() {
        return DeliverableType.Exam;
    }
}