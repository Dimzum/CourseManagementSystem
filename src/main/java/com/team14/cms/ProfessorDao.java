package com.team14.cms;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ProfessorDao {
    private static Map<Integer, Professor> professors = null;

    static {
        professors = new HashMap<Integer, Professor>();

        professors.put(1001, new Professor(1001, "Tom", "Smith", "tom"));
        professors.put(1002, new Professor(1002, "Harry", "Park", "harry"));
    }

    private static Integer nextId = 1003;

    public void add (Professor prof){
        if (prof.getId() == 0){
            prof.setId(nextId);
            professors.put(nextId++, prof);
        }else{
            professors.put(prof.getId(), prof);
        }
    }

    public Integer getNextId(){
        return nextId;
    }

    public Integer useNextId(){
        return nextId++;
    }

    public Collection<Professor> getAll(){
        return professors.values();
    }

    public Professor get(Integer id){
        return professors.get(id);
    }

    public void delete(Integer id){
        professors.remove(id);
    }
}