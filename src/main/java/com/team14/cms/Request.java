package com.team14.cms;

import java.util.logging.Handler;

public abstract class Request {
    protected Integer id;
    public boolean handled;
    public String type;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public boolean isHandled() {
        return handled;
    }

    public void handle(){
        handled = true;
    }
}

class StuSignupReq extends Request{
    public String lName, fName, birthday;
    public StuSignupReq(String fName, String lName, String birthday){
        this.fName = fName;
        this.lName = lName;
        this.birthday = birthday;
        this.handled = false;
        this.type = "stusignup";
    }
}

class ProfSignupReq extends Request{
    public String lName, fName;
    public ProfSignupReq(String fName, String lName){
        this.fName = fName;
        this.lName = lName;
        this.handled = false;
        this.type = "profsignup";
    }
}

