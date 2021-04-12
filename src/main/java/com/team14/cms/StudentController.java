package com.team14.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

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
    @Autowired
    CourseDeliverableDao courseDeliverableDao;

    @GetMapping(value = "/student/logout/{id}")
    public String logout(@PathVariable("id") Integer id) {
        Student student = studentDao.get(id);

        if (student != null) {
            student.logout();
        }

        return "loginStu";
    }

    @GetMapping(value = "/student/profile/{id}")
    public String profile(@PathVariable("id") Integer id, Model model) {
        if (studentDao.get(id) == null) {
            return "loginStu";
        }
        Student student = studentDao.get(id);
        if (!student.isLoggedIn()) {
            return "loginStu";
        }
        Collection<Course> taken = null;
        for (Integer cid : student.Taken){
            if (courseDao.get(id) != null){
                taken.add(courseDao.get(id));
            }
        }
        model.addAttribute("taken", taken);
        model.addAttribute("student", student);
        model.addAttribute("id", id);
        return "student/profile";
    }

    @GetMapping(value = "/student/courselist/{id}")
    public String getCourseList(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("courses", studentDao.get(id).coursesTaken);
        model.addAttribute("id", id);
        return "student/courseList";
    }

    @GetMapping(value = "/student/coursesearch/{id}")
    public String chooseCourse(@PathVariable("id") Integer stuId, Model model) {
        model.addAttribute("id", stuId);
        model.addAttribute("courses", courseDao.getAll());
        return "student/addCourse";
    }

    @GetMapping(value = "/student/chooseCourse")
    public String addCourse(@RequestParam("courseId") Integer courseId, Model model, @RequestParam("id") Integer studentId) {
        Student student = studentDao.get(studentId);
        if (student == null || !student.isLoggedIn){
            return "loginStu";
        }
        Course course = courseDao.get(courseId);
        if (course == null){
            model.addAttribute("id", (studentId));
            model.addAttribute("courses", studentDao.get(studentId).coursesTaken);
            return "student/courseList";
        }

        boolean result = student.registerInCourse(course);

        if (result) {
            model.addAttribute("id", (studentId));
            model.addAttribute("courses", studentDao.get(studentId).coursesTaken);
            return "student/courseList";
        } else {
            model.addAttribute("id", (studentId));
            return "student/addCourseFail";
        }
    }

    @GetMapping(value = "/student/deleteCourse")
    public String deleteCourse(@RequestParam("courseId") Integer courseId, Model model, @RequestParam("id") Integer studentId) {
        studentDao.get(studentId).coursesTaken.remove(courseDao.get((courseId)));
        model.addAttribute("id", (studentId));
        model.addAttribute("courses", studentDao.get(studentId).coursesTaken);
        return "student/courseList";
    }

    @GetMapping(value = "/student/submitFile/{courseId}/{id}")
    public String submitFile(@PathVariable("courseId") Integer courseId, Model model, @PathVariable("id") Integer studentId) {
        int size = courseDao.get(courseId).mycourseList.size();
        if (size > 0) {
            CourseDeliverable cd = courseDao.get(courseId).mycourseList.get(0);
            model.addAttribute("id", studentId);
            model.addAttribute("deliver", cd);
            model.addAttribute("courseId", courseId);
            return "student/submitFile";
        }
        return "student/noDeliverable";
    }

    @GetMapping(value = "/student/uploadFile/{id}")
    public String uploadFile(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id",id);
        return "student/upLoadSuccess";
    }
}