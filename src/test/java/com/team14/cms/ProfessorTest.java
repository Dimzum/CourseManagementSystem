package com.team14.cms;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProfessorTest {
    private Professor prof;

    @BeforeEach
    public void setup() {
        prof = new Professor(1001, "prof", "prof", "pass");
    }

    public void loginTest() {
        assertEquals(false, prof.isLoggedIn);
        prof.login();
        assertEquals(true, prof.isLoggedIn);
    }

    public void logoutTest() {
        assertEquals(false, prof.isLoggedIn);
        prof.login();
        assertEquals(true, prof.isLoggedIn);
        prof.logout();
        assertEquals(false, prof.isLoggedIn);
    }

    @Test
    public void createCourseDeliverableTest() {
        prof.courses.add(new Course());

        assertEquals(true, prof.courses.get(0).courseDeliverables.isEmpty());

        CourseDeliverable cd = prof.cdFactory.createCourseDeliverable(CourseDeliverable.DeliverableType.Assignment, "Test1", "8/3/2010");
        prof.courses.get(0).courseDeliverables.put(cd, null);

        assertEquals(false, prof.courses.get(0).courseDeliverables.isEmpty());
        //assertEquals(cd, prof.courses.get(0).courseDeliverables.keySet().toArray()[0]);
        assertThat("Same instance of cd", cd, equalTo(prof.courses.get(0).courseDeliverables.keySet().toArray()[0]));
    }
}
