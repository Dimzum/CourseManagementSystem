package com.team14.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @GetMapping(value = "/student/courseList/{id}")
    public String getCourseList(@PathVariable("id") Integer id,Model model)
    {   model.addAttribute("courses",studentDao.get(id).courses);
        return "student/courseList";
    }
    @GetMapping(value = "/student/coursesearch/{id}")
    public String chooseCourse(@PathVariable("id") Integer stuId,Model model)
    {   model.addAttribute("id",stuId);
        model.addAttribute("courses",courseDao.getAll());
        return "student/addCourse";
    }
    @GetMapping(value = "/student/addCourse")
    public String addCourse(@RequestParam("courseId") Integer courseId, Model model, @RequestParam("id") Integer studentId)
    {
        if(!studentDao.get(studentId).coursesTaken.contains(courseDao.get((courseId))))studentDao.get(studentId).coursesTaken.add(courseDao.get((courseId)));
        model.addAttribute("id",(studentId));

        model.addAttribute("courses",studentDao.get(studentId).coursesTaken);
        return "student/courseList";
    }
    @GetMapping(value = "/student/deleteCourse")
    public String deleteCourse(@RequestParam("courseId") Integer courseId, Model model, @RequestParam("id") Integer studentId)
    {
        studentDao.get(studentId).coursesTaken.remove(courseDao.get((courseId)));
        model.addAttribute("id",(studentId));
        model.addAttribute("courses",studentDao.get(studentId).coursesTaken);
        return "student/courseList";
    }


}