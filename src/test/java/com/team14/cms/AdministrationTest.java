package com.team14.cms;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdministrationTest {
    private Administration admin;

    @BeforeEach
    public void setup() throws Exception {
        admin = new Administration(1001, "admin", "admin", "pass");
    }

    @Test
    public void loginTest() {
        assertEquals(false, admin.isLoggedIn);
        admin.login();
        assertEquals(true, admin.isLoggedIn);
    }

    @Test
    public void logoutTest() {
        assertEquals(false, admin.isLoggedIn);
        admin.login();
        assertEquals(true, admin.isLoggedIn);
        admin.logout();
        assertEquals(false, admin.isLoggedIn);
    }

    @Test
    public void createCourseTest() {
        assertEquals(true, admin.courses.isEmpty());

        admin.createCourse("physics", 10021, 0.5f, true);

        assertEquals("physics", admin.courses.get(0).name);
        assertEquals(10021, admin.courses.get(0).getId());
    }

    @Test
    public void deleteCourseTest() {
        admin.createCourse("calculus", 13201, 0.5f, true);
        assertEquals(false, admin.courses.isEmpty());
        admin.deleteCourse(13201);
        assertEquals(true, admin.courses.isEmpty());
    }

    @Test
    public void createProfessorTest() {
        assertEquals(true, admin.professorList.isEmpty());

        admin.createProf(1002, "p", "p", "p");

        assertEquals(1002, admin.professorList.get(0).getId());
        assertEquals("p", admin.professorList.get(0).getFirstName());
        assertEquals("p", admin.professorList.get(0).getPassword());
    }

    @Test
    public void deleteProfessorTest() {
        admin.createProf(1002, "p", "p", "p");
        assertEquals(false, admin.professorList.isEmpty());
        admin.deleteProf(1002);
        assertEquals(true, admin.professorList.isEmpty());
    }

    @Test
    public void setProfInCourseTest() {
        Professor p = new Professor(1003, "prof", "prof", "pass");
        Course c = new Course();

        assertEquals(null, c.getProf());
        admin.setProfInCourse(c, p);
        assertEquals(p.id, c.getProf().id);
    }

    @Test
    public void createStudentTest() {
        assertEquals(true, admin.studentList.isEmpty());

        admin.createStudent(1003, "s", "s", "p", null);

        assertEquals(1003, admin.studentList.get(0).getId());
        assertEquals("s", admin.studentList.get(0).getFirstName());
        assertEquals("p", admin.studentList.get(0).getPassword());
    }

    @Test
    public void deleteStudentTest() {
        admin.createStudent(1003, "s", "s", "p", null);
        assertEquals(false, admin.studentList.isEmpty());
        admin.deleteStudent(1003);
        assertEquals(true, admin.studentList.isEmpty());
    }

    /*
    @Test
    public void registerStudentInCourseTest() {
        Student student = new Student(1004, null, null, null, null);
        Course c = new Course();

        assertEquals(false, c.classList.containsKey(student));
        student.registerInCourse(c);
        admin.registerStudentInCourse(c);
        assertEquals(true, c.classList.containsKey(student));
    }
    */
}
