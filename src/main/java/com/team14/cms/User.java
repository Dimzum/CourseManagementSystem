package com.team14.cms;

public abstract class User implements Observer {
    public Integer id;
    public String fname, lname, birthday;
    protected String password;
    public boolean loggedin;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return fname;
    }
    public void setFirstName(String fname) {
        this.fname = fname;
    }

    public String getLastName() {
        return lname;
    }
    public void setLastName(String fname) {
        this.lname = lname;
    }

    public boolean isLoggedin() {
        return loggedin;
    }
    public void login(){
        loggedin = true;
    }
    public void logout(){
        loggedin = false;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
