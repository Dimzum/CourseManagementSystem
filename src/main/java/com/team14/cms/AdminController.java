package com.team14.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import java.util.Collection;

@Controller
public class AdminController {
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
    public String saveProf(@PathVariable("id") Integer id, @RequestParam(value = "reqid", required = false) Integer reqid, @RequestParam("fName") String fName, @RequestParam("lName") String lName,  Model model){
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
        if (reqid != null){
            Request req = requestDao.get(reqid);
            req.handle();
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

    @PostMapping(value = "/student/save/{id}")
    public String saveStu(@PathVariable("id") Integer id, @RequestParam("fName") String fName, @RequestParam("lName") String lName, @RequestParam("birthday") String birthday, @RequestParam(value = "reqid", required = false) Integer rid, @RequestParam("taken") String taken, Model model){
        Administration admin = adminDao.get(1001);

        if (!admin.isLoggedIn()){
            return "loginAdmin";
        }

        Student student = new Student(id, fName, lName, "123456", birthday);
        String[] lis = taken.split(",");
        Integer cid;
        List<Integer> Taken = new ArrayList<Integer>();
        for (String c : lis){
            cid = Integer.valueOf(c);
            if (courseDao.get(cid) != null){
                Taken.add(cid);
            }
        }
        student.Taken = Taken;

        if (student.getId() == studentDao.getNextId()){
            student.setId(studentDao.useNextId());
            studentDao.add(student);
        }else{
            studentDao.add(student);
        }

        if (rid != null){
            Request req = requestDao.get(rid);
            req.handle();
        }

        Collection<Student> students = studentDao.getAll();
        model.addAttribute("students", students);

        return "admin/students";
    }

    @GetMapping(value = "/admin/courselist")
    public String courselist(Model model) {
        Administration admin = adminDao.get(1001);

        if (!admin.isLoggedIn()){
            return "loginAdmin";
        }
        Collection<Course> courses = courseDao.getAll();
        model.addAttribute("courses", courses);
        return "admin/courses";
    }

    @PostMapping(value = "/course/delete/{id}")
    public String delCourse(@PathVariable("id") Integer id, Model model) {
        Administration admin = adminDao.get(1001);

        if (!admin.isLoggedIn()){
            return "loginAdmin";
        }

        if (courseDao.get(id) != null) {
            courseDao.delete(id);
        }
        Collection<Course> courses = courseDao.getAll();
        model.addAttribute("courses", courses);
        return "admin/courses";
    }

    @GetMapping(value = "/course/add")
    public String goToAddCoursePage(Model model) {
        Administration admin = adminDao.get(1001);
        Collection<Professor> professors = professorDao.getAll();

        if (!admin.isLoggedIn()){
            return "loginAdmin";
        }

        Integer id = courseDao.getNextId();
        model.addAttribute("id", id);
        model.addAttribute("profs", professors);
        return "admin/addCourseForm";
    }

    @PostMapping(value = "/course/save/{id}")
    public String saveCourse(@PathVariable("id") Integer id, @RequestParam("name") String name, @RequestParam("credit") double credit, @RequestParam("pid") Integer pid, @RequestParam("prereq") String prereq, @RequestParam("preclu") String preclu, Model model) {
        Administration admin = adminDao.get(1001);

        if (!admin.isLoggedIn()){
            return "loginAdmin";
        }

        if (professorDao.get(pid) == null){
            return "admin/courses";
        }
        Professor professor = professorDao.get(pid);



        List<Course> prerequisites = new ArrayList<>();
        List<Course> preclusions = new ArrayList<>();
        String[] lis1 = prereq.split(",");
        String[] lis2 = preclu.split(",");
        Integer cid;


        for (String c : lis1){
            if (c.equals("")){
                continue;
            }
            cid = Integer.valueOf(c);
            if (courseDao.get(cid) != null) {
                prerequisites.add(courseDao.get(cid));
            }
        }
        for (String c : lis2){
            if (c.equals("")){
                continue;
            }
            cid = Integer.valueOf(c);
            if (courseDao.get(cid) != null) {
                preclusions.add(courseDao.get(cid));
            }
        }
        Course course = new Course(name, id, credit, prerequisites, preclusions, true);

        if (course.getId() == courseDao.getNextId()) {
            course.setId(courseDao.useNextId());
            courseDao.add(course);
        } else {
            courseDao.add(course);
        }

        course.setProf(professor);
        professor.courses.add(course);

        Collection<Course> courses = courseDao.getAll();
        model.addAttribute("courses", courses);
        return "admin/courses";
    }


    @GetMapping(value = "/admin/intimereq")
    public String intimereq(Model model){
        Administration admin = adminDao.get(1001);

        if (!admin.isLoggedIn()){
            return "loginAdmin";
        }
        Collection<Request> requests = requestDao.getAll();
        model.addAttribute("requests", requests);
        return "admin/normalreq";
    }

    @PostMapping(value = "/request/delete/{id}")
    public String intimereq(@PathVariable("id") Integer id, Model model){
        Administration admin = adminDao.get(1001);

        if (!admin.isLoggedIn()){
            return "loginAdmin";
        }

        if (requestDao.get(id) != null){
            requestDao.delete(id);
        }
        Collection<Request> requests = requestDao.getAll();
        model.addAttribute("requests", requests);
        return "admin/normalreq";
    }

    @GetMapping(value = "/request/handle/{id}")
    public String handle(@PathVariable("id") Integer id, Model model){
        Administration admin = adminDao.get(1001);

        if (!admin.isLoggedIn()){
            return "loginAdmin";
        }
        if (requestDao.get(id) == null){
            return "admin/normalreq";
        }

        Request req = requestDao.get(id);

        model.addAttribute("req", req);
        if (req.type == "stusignup"){
            Integer sid = studentDao.getNextId();
            model.addAttribute("id", sid);
            return "admin/addStu";
        }else if(req.type == "profsignup"){
            Integer pid = professorDao.getNextId();
            model.addAttribute("id", pid);
            return "admin/addProf";
        }

        Collection<Request> requests = requestDao.getAll();
        model.addAttribute("requests", requests);
        return "admin/normalreq";
    }

    @PostMapping(value = "/request/reject/{id}")
    public String reject(@PathVariable("id") Integer id, Model model){
        Administration admin = adminDao.get(1001);

        if (!admin.isLoggedIn()){
            return "loginAdmin";
        }
        if (requestDao.get(id) == null){
            return "admin/normalreq";
        }

        Request req = requestDao.get(id);

        req.handle();

        Collection<Request> requests = requestDao.getAll();
        model.addAttribute("requests", requests);
        return "admin/normalreq";
    }

    @GetMapping(value = "/admin/latereq")
    public String latereq(){
        return "admin/latereq";
    }
}
