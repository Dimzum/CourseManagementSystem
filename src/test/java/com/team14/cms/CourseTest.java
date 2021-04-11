package com.team14.cms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {
    private Course course;

    @BeforeEach
    public void setup() throws Exception {
        course = new Course();
    }

    @Test
    @DisplayName("Testing if student is correctly added to course")
    public void addToCourseTest() {
        Student student = new Student(101, "john", "smith", "password", "2020/01/02");
        course.addToCourse(student);

        assertEquals(101, course.getStudentByID(student.getId()).getId());
        assertEquals("password", course.getStudentByID(student.getId()).getPassword());
    }
}
