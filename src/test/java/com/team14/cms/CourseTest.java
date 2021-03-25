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
    public void testAddToCourse() {
        Student student = new Student(101, "john", "jay", "password", "2020/01/02");
        course.addToCourse(student);
<<<<<<< Updated upstream

        assertEquals(student.getId(), course.getStudent(student.getId()).getId());
        assertEquals(student.getName(), course.getStudent(student.getId()).getName());
        assertEquals(student.getPassword(), course.getStudent(student.getId()).getPassword());
=======
        //assertEquals(student.getId(), course.getStudent(student.getId()).getId());
>>>>>>> Stashed changes
    }
}