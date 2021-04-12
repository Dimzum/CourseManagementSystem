package com.team14.cms;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;


public class StudentTest {
    Student student;

    @BeforeEach
    public void setup() {
        student = new Student(1001, "s", "s", "pass", "2/3/2010");
    }

    @Test
    public void loginTest() {
        assertEquals(false, student.isLoggedIn);
        student.login();
        assertEquals(true, student.isLoggedIn);
    }

    @Test
    public void logoutTest() {
        assertEquals(false, student.isLoggedIn);
        student.login();
        assertEquals(true, student.isLoggedIn);
        student.logout();
        assertEquals(false, student.isLoggedIn);
    }

    @Test
    public void registerInCourseTest() {
        Course c = new Course();

        assertEquals(false, c.classList.containsKey(student));
        student.registerInCourse(c);
        assertEquals(true, c.classList.containsKey(student));
    }

    @Test
    public void dropCourseTest() {
        Course c = new Course();
        c.addToCourse(student);

        assertEquals(true, c.classList.containsKey(student));
        student.dropCourse(c);
        assertEquals(false, c.classList.containsKey(student));
    }

    @Test
    public void submitCourseDeliverableTest() {
        Course c = new Course();
        CourseDeliverable cd = new Assignment("a1", "2/10/21");
        c.courseDeliverables.put(cd, new HashMap<>());

        assertEquals(false, c.courseDeliverables.get(cd).containsKey(student));
        student.submitCourseDeliverable(c, cd);
        assertEquals(true, c.courseDeliverables.get(cd).containsKey(student));
    }
}
