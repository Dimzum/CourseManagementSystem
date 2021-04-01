package com.team14.cms;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CourseDao {
    private static Map<Integer, Course> courseMap = null;

    static {
        courseMap = new HashMap<Integer, Course>();
        courseMap.put(10001, new Course("COMP1405",10001, 0.5, null,false) );
        courseMap.put(10002, new Course("COMP1406",10002, 0.5, null,false) );
    }

    public static Integer getNextId() {
        return nextId;
    }

    private static Integer nextId = 10003;

    public void add (Course course){
        course.setId(nextId);
        courseMap.put(nextId++, course);
    }

    public Collection<Course> getAll(){
        return courseMap.values();
    }
    public Integer useNextId(){
        return nextId++;
    }
    public Course get(Integer id){
        return courseMap.get(id);
    }

    public void delete(Integer id){
        courseMap.remove(id);
    }
}
