package com.team14.cms;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class LoginController{
    abstract String login(String username, String password);
}

@Controller
class LoginstuController extends LoginController {
    @PostMapping(value = "/student/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password){
        if (!("".equals(username) || username == null) && "123456".equals(password)){
            return "loginProf";
        }else{
            //map.put("msg", "Username or Password is wrong.");
            return "loginStu";
        }
    }
}

@Controller
class LoginadminController extends LoginController {
    @PostMapping(value = "/admin/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password){
        if (!("".equals(username) || username == null) && "123456".equals(password)){
            return "loginProf";
        }else{
            //map.put("msg", "Username or Password is wrong.");
            return "loginAdmin";
        }
    }
}

@Controller
class LoginprofController extends LoginController {
    @PostMapping(value = "/professor/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password){
        if (!("".equals(username) || username == null) && "123456".equals(password)){
            return "loginStu";
        }else{
            //map.put("msg", "Username or Password is wrong.");
            return "loginProf";
        }
    }
}