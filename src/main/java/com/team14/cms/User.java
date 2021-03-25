package com.team14.cms;

public abstract class User implements Observer {
    protected int id;
    protected String fName, lName, password;
    public boolean isLoggedIn;

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

    public boolean isLoggedin() {
        return isLoggedIn;
    }

    public void login() {
        toggleLogin();
    }
    public void logout() {
        toggleLogin();
    }

    public void toggleLogin() {
        isLoggedIn = !isLoggedIn;
    }
}
