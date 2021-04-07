package com.team14.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

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

    @GetMapping(value = "/professor/profCourses/{id}")
    public String courselist(@PathVariable("id") Integer id, Model model) {
        Professor professor = professorDao.get(id);

        if (!professor.isLoggedIn()) {
            return "loginProf";
        }

        Collection<Course> courses = professor.courses;
        model.addAttribute("courses", courses);
        return "professor/profCourses";
    }

    @PostMapping(value = "/professor/createCourseDeliverable/{id}")
    public String createCourseDeliverable(@PathVariable("id") Integer id,
                                          @RequestParam("cid") Integer cid,
                                          @RequestParam("type") CourseDeliverable.DeliverableType type,
                                          @RequestParam("name") String name,
                                          @RequestParam("deadline") String deadline,
                                          Model model) {
        Professor professor = professorDao.get(id);

        if (!professor.isLoggedIn()) {
            return "loginProf";
        }

        professor.createCourseDeliverable(cid, type, name, deadline);
        model.addAttribute("professor", professor);
        return "professor/createCourseDeliverable";
    }

    @PostMapping(value = "/professor/deleteCourseDeliverable/{id}")
    public String deleteCourseDeliverable(@PathVariable("id") Integer id,
                                          @RequestParam("cid") Integer cid,
                                          @RequestParam("name") String name,
                                          Model model) {
        Professor professor = professorDao.get(id);

        if (!professor.isLoggedIn()) {
            return "loginProf";
        }

        professor.deleteCourseDeliverable(cid, name);
        model.addAttribute("professor", professor);
        return "professor/deleteCourseDeliverable";
    }

}