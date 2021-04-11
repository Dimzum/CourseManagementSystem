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
        model.addAttribute("id", id);
        model.addAttribute("courses", courses);
        return "professor/profCourses";
    }

    @GetMapping(value = "/professor/{pid}/coursePage/{id}")
    public String courselist(@PathVariable("pid") Integer pid, @PathVariable("id") Integer id, Model model) {
        Professor professor = professorDao.get(pid);

        if (!professor.isLoggedIn()) {
            return "loginProf";
        }
        if (courseDao.get(id) == null){
            return "professor/profCourses";
        }

        Course course = courseDao.get(id);

        Collection<Course> courses = professor.courses;

        if (!courses.contains(course)){
            return "professor/profCourses";
        }
        model.addAttribute("id", pid);
        model.addAttribute("course", course);
        return "professor/coursePage";
    }

    @GetMapping(value = "/professor/{pid}/createCourseDeliverable/{id}")
    public String createCourseDeliverable(@PathVariable("pid") Integer pid, @PathVariable("id") Integer id, Model model) {
        Professor professor = professorDao.get(pid);

        if (!professor.isLoggedIn()) {
            return "loginProf";
        }
        model.addAttribute("id", pid);
        if (courseDao.get(id) == null){
            return "professor/profCourses";
        }

        Course course = courseDao.get(id);

        Collection<Course> courses = professor.courses;

        if (!courses.contains(course)){
            return "professor/profCourses";
        }


        model.addAttribute("course", course);
        return "professor/createCourseDeliverable";
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

    @GetMapping(value = "/professor/deleteCourseDeliverable/{id}")
    public String deleteCourseDeliverable(@PathVariable("id") Integer id, Model model) {
        Professor professor = professorDao.get(id);

        if (!professor.isLoggedIn()) {
            return "loginProf";
        }

        model.addAttribute("professor", professor);
        return "professor/deleteCourseDeliverable";
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