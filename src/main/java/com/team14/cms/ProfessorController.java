package com.team14.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfessorController {
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

    @GetMapping(value = "/professor/logout/{id}")
    public String logout(@PathVariable("id") Integer id) {
        Professor professor = professorDao.get(id);

        professor.logout();

        return "loginProf";
    }

    @GetMapping(value = "/professor/profile/{id}")
    public String profile(@PathVariable("id") Integer id, Model model) {
        if (professorDao.get(id) == null){
            return "loginProf";
        }
        Professor professor = professorDao.get(id);
        if (!professor.isLoggedIn()) {
            return "loginProf";
        }
        model.addAttribute("professor", professor);
        return "professor/profile";
    }
}