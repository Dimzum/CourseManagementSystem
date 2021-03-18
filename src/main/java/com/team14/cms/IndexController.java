package com.team14.cms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {


    @GetMapping(value = {"/", "/index"})
    public String Index(){
        return "index";
    }

    @GetMapping(value = "/student/login")
    public String stuloginpage(){
        return "loginStu";
    }

    @GetMapping(value = "/professor/login")
    public String profloginpage(){
        return "loginProf";
    }

    @GetMapping(value = "/admin/login")
    public String adminloginpage(){
        return "loginAdmin";
    }
}
