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
    public void CreateCourse() {
        assertEquals(true, admin.courses.isEmpty());

        admin.createCourse("physics", 10021, 0.5f, true);

        assertEquals("physics", admin.courses.get(0).name);
        assertEquals(10021, admin.courses.get(0).getId());
    }

    @Test
    public void DeleteCourse() {
        admin.createCourse("calculus", 13201, 0.5f, true);
        assertEquals(false, admin.courses.isEmpty());
        admin.deleteCourse(13201);
        assertEquals(true, admin.courses.isEmpty());
    }

    @Test
    public void CreateProfessorTest() {
        assertEquals(true, admin.professorList.isEmpty());

        admin.createProf(1002, "p", "p", "p");

        assertEquals(1002, admin.professorList.get(0).getId());
        assertEquals("p", admin.professorList.get(0).getFirstName());
        assertEquals("p", admin.professorList.get(0).getPassword());
    }

    @Test
    public void DeleteProfessorTest() {
        admin.createProf(1002, "p", "p", "p");
        assertEquals(false, admin.professorList.isEmpty());
        admin.deleteProf(1002);
        assertEquals(true, admin.professorList.isEmpty());
    }

    @Test
    public void CreateStudentTest() {
        assertEquals(true, admin.studentList.isEmpty());

        admin.createStudent(1003, "s", "s", "p", null);

        assertEquals(1003, admin.studentList.get(0).getId());
        assertEquals("s", admin.studentList.get(0).getFirstName());
        assertEquals("p", admin.studentList.get(0).getPassword());
    }

    @Test
    public void DeleteStudentTest() {
        admin.createStudent(1003, "s", "s", "p", null);
        assertEquals(false, admin.studentList.isEmpty());
        admin.deleteStudent(1003);
        assertEquals(true, admin.studentList.isEmpty());
    }
}
