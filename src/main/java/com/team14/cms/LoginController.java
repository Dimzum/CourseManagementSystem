package com.team14.cms;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class LoginController{
    abstract String login(String username, String password, Model model);
}

@Controller
class LoginstuController extends LoginController {
    @Autowired
    StudentDao studentDao;


    @PostMapping(value = "/student/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model){
        Integer id = 0;
        try{
            id = Integer.valueOf(username);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        if (studentDao.get(id) == null || id == 0){
            return "loginStu";
        }
        Student student = studentDao.get(id);
        if (student.getPassword().equals(password)){
            student.login();
            model.addAttribute("id", id);
            return "student/main";
        }else{
            //map.put("msg", "Username or Password is wrong.");
            return "loginStu";
        }
    }
}

@Controller
class LoginadminController extends LoginController {
    @Autowired
    AdminDao adminDao;

    @PostMapping(value = "/admin/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model){
        Administration admin = adminDao.get(1001);
        if ("admin".equals(username) && "123456".equals(password)){
            admin.login();
            return "admin/main";
        }else{
            //map.put("msg", "Username or Password is wrong.");
            return "loginAdmin";
        }
    }
}

@Controller
class LoginprofController extends LoginController {
    @Autowired
    ProfessorDao professorDao;

    @PostMapping(value = "/professor/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model){
        Integer id = 0;
        try{
            id = Integer.valueOf(username);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        if (professorDao.get(id) == null || id == 0){
            return "loginProf";
        }
        Professor professor = professorDao.get(id);
        if (professor.getPassword().equals(password)){
            professor.login();
            model.addAttribute("id", id);
            return "professor/main";
        }else{
            //map.put("msg", "Username or Password is wrong.");
            return "loginProf";
        }
    }
}