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
        Course course=new Course("WEB","10001",false);
        course.setId(1001);
        courseMap.put(1001,course );
    }

    public static Integer getNextId() {
        return nextId;
    }

    private static Integer nextId = 1002;

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
