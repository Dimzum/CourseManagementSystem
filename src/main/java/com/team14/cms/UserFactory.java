package com.team14.cms;

public class UserFactory {
    public User getUser(String userType) {
        if (userType != null) {
            return null;
        }

        if (userType.equalsIgnoreCase("STUDENT")) {
            return new Student();
        } else if (userType.equalsIgnoreCase("PROFESSOR")) {
            return new Professor();
        } else if (userType.equalsIgnoreCase("ADMINISTRATION")) {
            return new Administration();
        }

        return null;
    }
}
