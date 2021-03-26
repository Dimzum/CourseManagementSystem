package com.team14.cms;

import java.util.ArrayList;
import java.util.List;

public abstract class User implements Observer {
    public boolean isLoggedIn;

    protected int id;
    protected String fName, lName, password;

    protected List<Course> courses = new ArrayList<>();

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return fName;
    }
    public void setFirstName(String fName) {
        this.fName = fName;
    }

    public String getLastName() {
        return lName;
    }
    public void setLastName(String lName) {
        this.lName = lName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void login() {
        toggleLogin();
    }
    public void logout() {
        toggleLogin();
    }

    private void toggleLogin() {
        isLoggedIn = !isLoggedIn;
    }
}
