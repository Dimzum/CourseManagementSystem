package com.team14.cms;

import java.util.List;

public interface Course {
    String name = null;
    String crn = null;
    float creditValue = 0;
    List<Course> prerequisites = null;

    boolean isOpen = false;
    List<Student> waitlist = null;
    int maxWaitlist = 10;

    Professor prof = null;
    List<Student> classList = null;

    public static boolean isWaitlistFull() {
        return (waitlist.size() == maxWaitlist);
    }
    public static Course createCourse()
    {
        return null;
    }
}
