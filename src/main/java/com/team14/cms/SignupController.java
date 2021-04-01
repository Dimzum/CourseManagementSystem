package com.team14.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class SignupController{
    abstract String signupPage();
}

@Controller
class SignupstuController extends SignupController {
    @Autowired
    RequestDao requestDao;

    @GetMapping(value = "/student/signup")
    public String signupPage(){
        return "signupStu";
    }

    @PostMapping(value = "/student/signup")
    public String signup(@RequestParam("fName") String fName, @RequestParam("lName") String lName, @RequestParam("birthday") String birthday){

        requestDao.add(new StuSignupReq(fName, lName, birthday));
        return "loginStu";
    }
}

@Controller
class SignupprofController extends SignupController {
    @Autowired
    RequestDao requestDao;

    @GetMapping(value = "/professor/signup")
    public String signupPage(){
        return "signupProf";
    }

    @PostMapping(value = "/professor/signup")
    public String signup(@RequestParam("fName") String fName, @RequestParam("lName") String lName){

        requestDao.add(new ProfSignupReq(fName, lName));
        return "loginProf";
    }
}