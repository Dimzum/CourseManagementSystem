package com.team14.cms;

public abstract class CourseDeliverable {
    public enum DeliverableType {
        Assignment,
        Test,
        Exam
    }

    protected String name;
    protected String dueDate;
    protected int grade;

    public CourseDeliverable() {

    }

    public CourseDeliverable(String name, String dueDate) {
        this.name = name;
        this.dueDate = dueDate;
    }

    public int getGrade() {
        return grade;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }

    protected abstract DeliverableType getType();
}

class Assignment extends CourseDeliverable {
    public Assignment(String name, String dueDate) {
        super(name, dueDate);
    }

    @Override
    protected DeliverableType getType() {
        return DeliverableType.Assignment;
    }
}

class Test extends CourseDeliverable {
    public Test(String name, String dueDate) {
        super(name, dueDate);
    }

    @Override
    protected DeliverableType getType() {
        return DeliverableType.Test;
    }
}

class Exam extends CourseDeliverable {
    public Exam(String name, String dueDate) {
        super(name, dueDate);
    }

    @Override
    protected DeliverableType getType() {
        return DeliverableType.Exam;
    }
}