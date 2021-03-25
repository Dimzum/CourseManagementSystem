package com.team14.cms;

import com.team14.cms.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller
public class AdminController {
    @Autowired
    AdminDao adminDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    ProfessorDao professorDao;

    @GetMapping(value = "/admin/profile")
    public String profile(Model model){
        Administration admin = adminDao.get(1001);

        model.addAttribute("id", admin.getId());
        model.addAttribute("name", "Admin");
        if (admin.isLoggedin()){
            return "admin/profile";
        }else{
            return "loginAdmin";
        }


    }

    @GetMapping(value = "/admin/proflist")
    public String proflist(Model model){
        Collection<Professor> professors = professorDao.getAll();

        model.addAttribute("professors", professors);

        return "admin/professors";
    }

    @GetMapping(value = "/admin/stulist")
    public String stulist(Model model){
        Collection<Student> students = studentDao.getAll();

        model.addAttribute("students", students);

        return "admin/students";
    }

    @GetMapping(value = "/admin/courselist")
    public String courselist(){
        return "admin/courses";
    }

    @GetMapping(value = "/admin/intimereq")
    public String intimereq(){
        return "admin/intimereq";
    }

    @GetMapping(value = "/admin/latereq")
    public String latereq(){
        return "admin/latereq";
    }
}
