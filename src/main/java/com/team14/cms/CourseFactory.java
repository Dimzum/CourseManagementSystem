package com.team14.cms;

public class CourseFactory {
    public Course getCourse()
    {
        Course publicCourse=new PublicCourse();
        Course proferssionalCourse=new ProferssionalCourse();
        return publicCourse;
    }
}
