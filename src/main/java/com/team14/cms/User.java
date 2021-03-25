package com.team14.cms;

public abstract class User implements Observer {
    public Integer id;
    public String fname, lname;
    protected String password;
    protected boolean loggedin;

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
    public void setLastName(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedin(){
        return this.loggedin;
    }
    public void login(){
        this.loggedin = true;
    }

    public void logout(){
        this.loggedin = false;
    }
}
