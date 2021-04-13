package com.team14.cms;

public abstract class CourseDeliverable {
    public enum DeliverableType {
        Assignment,
        Test,
        Exam
    }
    public Integer id;
    public String name;
    public String deadline;
    public Integer cid;

    public boolean isGraded = false;

    public CourseDeliverable() {

    }

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public void setCid(Integer cid){
        this.cid = cid;
    }
    public Integer getCid(){
        return cid;
    }
    public String getDeadline(){
        return deadline;
    }

    public void setDeadline(String deadline){
        this.deadline = deadline;
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