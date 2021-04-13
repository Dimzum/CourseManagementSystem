package com.team14.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
            a.setCid(cid);
            courseDeliverableDao.add(a);
            course.mycourseList.add(a);
        }else if (type == CourseDeliverable.DeliverableType.Test){
            Test t = new Test(name, deadline);
            t.setId(cdid);
            t.setCid(cid);
            courseDeliverableDao.add(t);
            course.mycourseList.add(t);
        }else if (type == CourseDeliverable.DeliverableType.Exam){
            Exam e = new Exam(name, deadline);
            e.setId(cdid);
            e.setCid(cid);
            courseDeliverableDao.add(e);
            course.mycourseList.add(e);
        }

        courseDeliverableDao.get(id).setCid(course.id);
        Collection<CourseDeliverable> cds = new ArrayList<>();
        for (CourseDeliverable cd : courseDeliverableDao.getAll()){
            if (cd.getCid().equals(cid)){
                cds.add(cd);
            }
        }
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
        Collection<CourseDeliverable> cds = new ArrayList<>();
        for (CourseDeliverable cd : courseDeliverableDao.getAll()){
            if (cd.getCid().equals(cid)){
                cds.add(cd);
            }
        }
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
        model.addAttribute("students", course.courseDeliverables.get(cd));
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
        Collection<CourseDeliverable> cds = new ArrayList<>();
        for (CourseDeliverable cd : courseDeliverableDao.getAll()){
            if (cd.getCid().equals(cid)){
                cds.add(cd);
            }
        }
        model.addAttribute("cds", cds);
        return "professor/coursePage";
    }

    @GetMapping(value = "/professor/{pid}/course/{cid}/cd/{cdid}")
    public String goToGradeDeliPage(@PathVariable("pid") Integer pid, @PathVariable("cid") Integer cid, @PathVariable("cdid") Integer cdid, Model model){
        Professor professor = professorDao.get(pid);

        if (!professor.isLoggedIn()) {
            return "loginProf";
        }
        Course course = courseDao.get(cid);

        Collection<Student> students = new ArrayList<>();
        if (course.classList.size() != 0 && course.classList != null){
            for (Student stu : course.classList.keySet()){
                stu.grade = course.classList.get(stu);
                students.add(stu);
            }
        }
        model.addAttribute("students", students);
        model.addAttribute("id", pid);
        model.addAttribute("course", courseDao.get(cid));
        CourseDeliverable cd = courseDeliverableDao.get(cdid);
        model.addAttribute("cd", cd);
        return "professor/allDeliGrade";
    }

    @GetMapping(value = "/professor/{pid}/course/{cid}/cd/{cdid}/student/{sid}/gradeIt")
    public String goToIndividualGrading(@PathVariable("pid") Integer pid, @PathVariable("cid") Integer cid, @PathVariable("cdid") Integer cdid, @PathVariable("sid") Integer sid, Model model){
        Professor professor = professorDao.get(pid);

        if (professor == null || !professor.isLoggedIn()) {
            return "loginProf";
        }
        model.addAttribute("id", pid);
        model.addAttribute("student", studentDao.get(sid));
        model.addAttribute("course", courseDao.get(cid));
        model.addAttribute("cd", courseDeliverableDao.get(cdid));

        return "professor/indiDeliGradeForm";
    }

    @PostMapping(value = "/professor/{pid}/course/{cid}/cd/{cdid}/student/{sid}/gradeIt")
    public String saveIndividualGrading(@PathVariable("pid") Integer pid, @PathVariable("cid") Integer cid, @PathVariable("cdid") Integer cdid, @PathVariable("sid") Integer sid, @RequestParam("grade") double grade, Model model){
        Professor professor = professorDao.get(pid);

        if (professor == null || !professor.isLoggedIn()) {
            return "loginProf";
        }
        Course course = courseDao.get(cid);

        Collection<Student> students = new ArrayList<>();
        System.out.println(course.classList.size());
        if (course.classList.size() != 0 && course.classList != null){
            for (Student stu : course.classList.keySet()){
                System.out.println(stu.getId());
                if (stu.getId() == sid){
                    if (grade < 0 || grade > 100){
                        model.addAttribute("id", pid);
                        model.addAttribute("student", studentDao.get(sid));
                        model.addAttribute("course", courseDao.get(cid));
                        model.addAttribute("cd", courseDeliverableDao.get(cdid));

                        return "professor/indiDeliGradeForm";
                    }
                    course.classList.put(stu, grade);
                }
                stu.grade = course.classList.get(stu);
                students.add(stu);
            }
        }
        model.addAttribute("students", students);
        model.addAttribute("id", pid);
        model.addAttribute("course", courseDao.get(cid));
        CourseDeliverable cd = courseDeliverableDao.get(cdid);
        model.addAttribute("cd", cd);
        return "professor/allDeliGrade";

    }

    @PostMapping(value = "/professor/{pid}/course/{cid}/cd/{cdid}/submitCdGrade")
    public String saveAllDeliGrade(@PathVariable("pid") Integer pid, @PathVariable("cid") Integer cid, @PathVariable("cdid") Integer cdid, Model model){
        Professor professor = professorDao.get(pid);

        if (professor == null || !professor.isLoggedIn()) {
            return "loginProf";
        }
        Course course = courseDao.get(cid);

        Collection<Student> students = new ArrayList<>();

        boolean check = true;
        if (course.classList.size() != 0 && course.classList != null){
            for (Student stu : course.classList.keySet()){
                System.out.println(stu.isHandIn);
                System.out.println(stu.grade);
                students.add(stu);
                if (stu.isHandIn == true && course.getGrade(stu.getId()) == -1){
                    System.out.println("wrong");
                    check = false;
                }
                if (stu.isHandIn == false && course.getGrade(stu.getId()) == -1){
                    course.classList.put(stu, 0.0);
                }
                if (stu.isHandIn == true && course.getGrade(stu.getId()) >= 0){
                    course.classList.put(stu, stu.grade);
                }
                stu.grade = course.classList.get(stu);
            }
        }
        if (!check){
            model.addAttribute("students", students);
            model.addAttribute("id", pid);
            model.addAttribute("course", courseDao.get(cid));
            CourseDeliverable cd = courseDeliverableDao.get(cdid);
            model.addAttribute("cd", cd);
            return "professor/allDeliGrade";
        }
        courseDeliverableDao.get(cdid).isGraded = true;
        model.addAttribute("id", pid);
        model.addAttribute("course", course);
        Collection<CourseDeliverable> cds = new ArrayList<>();
        for (CourseDeliverable cd : courseDeliverableDao.getAll()){
            if (cd.getCid().equals(cid)){
                cds.add(cd);
            }
        }
        model.addAttribute("cds", cds);
        return "professor/coursePage";
    }
}