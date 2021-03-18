package com.team14.cms;

import java.util.List;

public class Course {
    String name;
    String crn;
    float creditValue;
    List<Course> prerequisites;

    boolean isOpen;
    List<Student> waitlist;
    int maxWaitlist = 10;

    Professor prof;
    List<Student> classList;

    public boolean isWaitlistFull() {
        return (waitlist.size() == maxWaitlist);
    }
}
