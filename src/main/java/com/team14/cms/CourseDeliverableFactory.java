package com.team14.cms;

public class CourseDeliverableFactory {
    public CourseDeliverable createCourseDeliverable(CourseDeliverable.DeliverableType type, String name, String dueDate) {
        switch (type) {
            case Assignment:
                return new Assignment(name, dueDate);
            case Test:
                return new Test(name, dueDate);
            case Exam:
                return new Exam(name, dueDate);
            default:
                return null;
        }
    }
}
