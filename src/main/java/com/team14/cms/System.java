package com.team14.cms;

import java.util.List;

public class System extends Subject {
    // Lists for every user in the system
    List<User> administrationList;
    List<User> professorList;
    List<User> studentList;

    // List for every course in the system
    List<Course> courseList;

    @Override
    public void notifyObservers() {
        for (Observer o: observers) {
            o.update();
        }
    }
}
