package com.team14.cms;

import java.util.List;

public class Course {
    String name;
    String crn;
    float creditValue;
    List<Course> prerequisites;

    boolean isOpen;
    List<Student> waitlist;

    Professor prof;
    List<Student> classList;
}
