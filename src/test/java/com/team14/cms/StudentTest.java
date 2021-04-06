package com.team14.cms;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    Student student;

    @BeforeEach
    public void setup() {
        student = new Student(1001, "s", "s", "pass", "2/3/2010");
    }
}
