package com.team14.cms;

public class CourseDeliverableFactory {
    public CourseDeliverable createCourseDeliverable(CourseDeliverable.DeliverableType type, String name, String deadline) {
        switch (type) {
            case Assignment:
                return new Assignment(name, deadline);
            case Test:
                return new Test(name, deadline);
            case Exam:
                return new Exam(name, deadline);
            default:
                return null;
        }
    }
}
