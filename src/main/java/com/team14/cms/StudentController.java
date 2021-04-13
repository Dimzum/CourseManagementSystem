package com.team14.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
        Collection<Course> taken = new ArrayList<>();
        if (student.Taken != null) {
            for (Integer cid : student.Taken) {
                if (courseDao.get(cid) != null) {
                    taken.add(courseDao.get(cid));
                }
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
            courseDao.get(courseId).addToCourse(student);
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
        Student student = studentDao.get(studentId);
        if (student == null || !student.isLoggedIn){
            return "loginStu";
        }
        studentDao.get(studentId).coursesTaken.remove(courseDao.get((courseId)));
        model.addAttribute("id", (studentId));
        model.addAttribute("courses", studentDao.get(studentId).coursesTaken);
        return "student/courseList";
    }

    @GetMapping(value = "/student/submitFile/{courseId}/{id}")
    public String submitFile(@PathVariable("courseId") Integer courseId, Model model, @PathVariable("id") Integer studentId) {
        Student student = studentDao.get(studentId);
        if (student == null || !student.isLoggedIn){
            return "loginStu";
        }
        int size = courseDao.get(courseId).mycourseList.size();
        if (size > 0) {
            CourseDeliverable cd = courseDao.get(courseId).mycourseList.get(0);
            model.addAttribute("id", studentId);
            model.addAttribute("deliver", cd);
            model.addAttribute("courseId", courseId);
            studentDao.get(studentId).submitCourseDeliverable(courseDao.get(courseId), cd);
            return "student/submitFile";
        }
        return "student/noDeliverable";
    }

    @GetMapping(value = "/student/uploadFile/{cid}/{id}")
    public String uploadFile(@PathVariable("cid") Integer cid, @PathVariable("id") Integer id, Model model) {
        Student student = studentDao.get(id);
        if (student == null || !student.isLoggedIn){
            return "loginStu";
        }
        Course course = courseDao.get(cid);

        if (course.classList.size() != 0 && course.classList != null){
            for (Student stu : course.classList.keySet()){
                System.out.println(stu.getId());
                if (stu.getId() == id){
                    System.out.println("same");
                    stu.isHandIn=true;
                    break;
                }
            }
        }

        model.addAttribute("id",id);
        return "student/upLoadSuccess";
    }
}