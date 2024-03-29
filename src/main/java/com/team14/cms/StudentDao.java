package com.team14.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class StudentDao implements UserDao {
    private static Map<Integer, Student> students = null;
    static {
        students = new HashMap<Integer, Student>();
        students.put(1001, new Student(1001, "Johny", "Chou", "johny", "2001/01/01"));
        students.put(1002, new Student(1002, "Miku", "Nakano", "miku", "2000/03/09"));
        students.put(1003, new Student(1003, "Alen", "Shi", "alen", "2002/04/10"));
        students.put(1004, new Student(1004, "Ray", "Stark", "ray", "2001/11/11"));
    }

    private static Integer nextId = 1005;

    public void add(Student stu) {
        if (stu.getId() == 0) {
            stu.setId(nextId);
            students.put(nextId++, stu);
        } else {
            students.put(stu.getId(), stu);
        }
    }

    public Integer getNextId() {
        return nextId;
    }

    public Integer useNextId() {
        return nextId++;
    }

    public Collection<Student> getAll() {
        return students.values();
    }

    public Student get(Integer id) {
        return students.get(id);
    }

    public Student get(String username) {

        for (Map.Entry entry : students.entrySet())
        {
            if(((Student)entry.getValue()).lName.equals(username)) return ((Student)entry.getValue());
        }
        return null;
    }

    public void delete(Integer id) {
        students.remove(id);
    }
}
