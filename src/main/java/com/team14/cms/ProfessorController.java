package com.team14.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

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

    @Autowired
    CourseDeliverableDao courseDeliverableDao;
    CourseDeliverableFactory cdFactory = new CourseDeliverableFactory();
    @GetMapping(value = "/professor/logout/{id}")
    public String logout(@PathVariable("id") Integer id) {
        Professor professor = professorDao.get(id);

        if (professor != null) {
            professor.logout();
        }

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
            Collection<Course> courses = professor.courses;
            model.addAttribute("id", pid);
            model.addAttribute("courses", courses);

            return "professor/profCourses";
        }

        Course course = courseDao.get(id);

        Collection<Course> courses = professor.courses;

        if (!courses.contains(course)){
            model.addAttribute("id", pid);
            model.addAttribute("courses", courses);
            return "professor/profCourses";
        }
        model.addAttribute("id", pid);
        model.addAttribute("course", course);
        Collection<CourseDeliverable> cds = courseDeliverableDao.getAll();
        model.addAttribute("cds", cds);
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

    @PostMapping(value = "/professor/{id}/createCourseDeliverable/{cid}")
    public String saveCourseDeliverable(@PathVariable("id") Integer id,
                                          @PathVariable("cid") Integer cid,
                                          @RequestParam("type") CourseDeliverable.DeliverableType type,
                                          @RequestParam("name") String name,
                                          @RequestParam("deadline") String deadline,
                                          Model model) {
        Professor professor = professorDao.get(id);

        if (!professor.isLoggedIn()) {
            return "loginProf";
        }
        Course course = courseDao.get(cid);

        Collection<Course> courses = professor.courses;

        if (course == null){
            model.addAttribute("id", id);
            model.addAttribute("courses", courses);
            return "professor/profCourses";
        }
        model.addAttribute("id", id);
        model.addAttribute("course", course);
        Integer cdid = courseDeliverableDao.getNextId();
        professor.createCourseDeliverable(cid, type, name, deadline);
        if (type == CourseDeliverable.DeliverableType.Assignment){
            Assignment a = new Assignment(name, deadline);
            a.setId(cdid);
            courseDeliverableDao.add(a);
            course.mycourseList.add(a);
        }else if (type == CourseDeliverable.DeliverableType.Test){
            Test t = new Test(name, deadline);
            t.setId(cdid);
            courseDeliverableDao.add(t);
            course.mycourseList.add(t);
        }else if (type == CourseDeliverable.DeliverableType.Exam){
            Exam e = new Exam(name, deadline);
            e.setId(cdid);
            courseDeliverableDao.add(e);
            course.mycourseList.add(e);
        }

        courseDeliverableDao.get(id).setCid(course.id);
        Collection<CourseDeliverable> cds = courseDeliverableDao.getAll();
        model.addAttribute("cds", cds);
        return "professor/coursePage";
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

    @PostMapping(value = "/professor/{pid}/course/{cid}/deliverable/{id}/delete")
    public String deleteCourseDeliverable(@PathVariable("pid") Integer pid,
                                          @PathVariable("cid") Integer cid,
                                          @PathVariable("id") Integer id,
                                          Model model) {
        Professor professor = professorDao.get(pid);

        if (!professor.isLoggedIn()) {
            return "loginProf";
        }
        Course course = courseDao.get(cid);

        Collection<Course> courses = professor.courses;

        if (course == null){
            model.addAttribute("id", pid);
            model.addAttribute("courses", courses);
            return "professor/profCourses";
        }
        professor.deleteCourseDeliverable(cid, courseDeliverableDao.get(id).name);
        courseDeliverableDao.delete(id);
        for (CourseDeliverable cd : courseDao.get(cid).mycourseList){
            if (cd.getId().equals(id)){
                courseDao.get(cid).mycourseList.remove(0);
                break;
            }
        }
        model.addAttribute("id", pid);
        model.addAttribute("course", course);
        Collection<CourseDeliverable> cds = courseDeliverableDao.getAll();
        model.addAttribute("cds", cds);
        return "professor/coursePage";
    }

    @GetMapping(value = "/professor/{pid}/course/{cid}/deliverable/{id}/edit")
    public String eidtCourseDeliverable(@PathVariable("pid") Integer pid,
                                          @PathVariable("cid") Integer cid,
                                          @PathVariable("id") Integer id,
                                          Model model) {
        Professor professor = professorDao.get(pid);

        if (!professor.isLoggedIn()) {
            return "loginProf";
        }
        Course course = courseDao.get(cid);

        Collection<Course> courses = professor.courses;

        if (course == null){
            model.addAttribute("id", pid);
            model.addAttribute("courses", courses);
            return "professor/profCourses";
        }
        model.addAttribute("id", pid);
        model.addAttribute("course", course);
        CourseDeliverable cd = courseDeliverableDao.get(id);
        if (cd == null){
            Collection<CourseDeliverable> cds = courseDeliverableDao.getAll();
            model.addAttribute("cds", cds);
            return "professor/coursePage";
        }
        model.addAttribute("cd", cd);
        return "professor/modifyCourseDeliverable";
    }

    @PostMapping(value = "/professor/{pid}/course/{cid}/deliverable/{id}/edit")
    public String saveEidtCourseDeliverable(@PathVariable("pid") Integer pid,
                                        @PathVariable("cid") Integer cid,
                                        @PathVariable("id") Integer id,
                                        @RequestParam("deadline") String deadline,
                                        Model model) {
        Professor professor = professorDao.get(pid);

        if (!professor.isLoggedIn()) {
            return "loginProf";
        }
        Course course = courseDao.get(cid);

        Collection<Course> courses = professor.courses;

        if (course == null){
            model.addAttribute("id", pid);
            model.addAttribute("courses", courses);
            return "professor/profCourses";
        }
        model.addAttribute("id", pid);
        model.addAttribute("course", course);
        courseDeliverableDao.get(id).setDeadline(deadline);
        Collection<CourseDeliverable> cds = courseDeliverableDao.getAll();
        model.addAttribute("cds", cds);
        return "professor/coursePage";
    }

}