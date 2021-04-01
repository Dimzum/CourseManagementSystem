package com.team14.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StudentController {
    @Autowired
    AdminDao adminDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    ProfessorDao professorDao;

    @Autowired
    CourseDao courseDao;

    @Autowired
    RequestDao requestDao;

    @GetMapping(value = "/student/logout/{id}")
    public String logout(@PathVariable("id") Integer id) {
        Student student = studentDao.get(id);

        student.logout();

        return "loginStu";
    }

    @GetMapping(value = "/student/profile/{id}")
    public String profile(@PathVariable("id") Integer id, Model model) {
        if (studentDao.get(id) == null){
            return "loginStu";
        }
        Student student = studentDao.get(id);
        if (!student.isLoggedIn()) {
            return "loginStu";
        }
        model.addAttribute("student", student);
        return "student/profile";
    }
}