package com.team14.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class AdminController {
    @Autowired
    AdminDao adminDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    ProfessorDao professorDao;

    @GetMapping(value = "/admin/logout")
    public String logout(){
        Administration admin = adminDao.get(1001);

        admin.logout();

        return "loginAdmin";
    }

    @GetMapping(value = "/admin/profile")
    public String profile(Model model){
        Administration admin = adminDao.get(1001);

        if (!admin.isLoggedIn()){
            return "loginAdmin";
        }
        model.addAttribute("id", admin.getId());
        model.addAttribute("name", "Admin");
        return "admin/profile";
    }

    @GetMapping(value = "/admin/proflist")
    public String proflist(Model model){
        Administration admin = adminDao.get(1001);

        if (!admin.isLoggedIn()){
            return "loginAdmin";
        }
        Collection<Professor> professors = professorDao.getAll();

        model.addAttribute("professors", professors);

        return "admin/professors";
    }

    @PostMapping (value = "/professor/delete/{id}")
    public String delprof(@PathVariable("id") Integer id, Model model){
        Administration admin = adminDao.get(1001);

        if (!admin.isLoggedIn()){
            return "loginAdmin";
        }
        if (professorDao.get(id) != null){
            professorDao.delete(id);
        }

        Collection<Professor> professors = professorDao.getAll();
        model.addAttribute("professors", professors);

        return "admin/professors";
    }

    @GetMapping(value = "/professor/add")
    public String goToAddPage(Model model){
        Administration admin = adminDao.get(1001);

        if (!admin.isLoggedIn()){
            return "loginAdmin";
        }

        Integer id = professorDao.getNextId();

        model.addAttribute("id", id);
        return "admin/addProfForm";
    }

    @PostMapping(value = "/professor/save/{id}")
    public String save(@PathVariable("id") Integer id, @RequestParam("fName") String fName, @RequestParam("lName") String lName, Model model){
        Administration admin = adminDao.get(1001);

        if (!admin.isLoggedIn()){
            return "loginAdmin";
        }

        Professor professor = new Professor(id, fName, lName, "123456");

        if (professor.getId() == professorDao.getNextId()){
            professor.setId(professorDao.useNextId());
            professorDao.add(professor);
        }else{
            professorDao.add(professor);
        }

        Collection<Professor> professors = professorDao.getAll();
        model.addAttribute("professors", professors);

        return "admin/professors";
    }

    @GetMapping(value = "/admin/stulist")
    public String stulist(Model model){
        Administration admin = adminDao.get(1001);

        if (!admin.isLoggedIn()){
            return "loginAdmin";
        }
        Collection<Student> students = studentDao.getAll();

        model.addAttribute("students", students);

        return "admin/students";
    }

    @PostMapping (value = "/student/delete/{id}")
    public String delstu(@PathVariable("id") Integer id, Model model){
        Administration admin = adminDao.get(1001);

        if (!admin.isLoggedIn()){
            return "loginAdmin";
        }
        if (studentDao.get(id) != null){
            studentDao.delete(id);
        }

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
