package com.team14.cms;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CourseDeliverableDao {
    private static Map<Integer, CourseDeliverable> cds = null;

    static {
        cds = new HashMap<Integer, CourseDeliverable>();
    }

    public static Integer getNextId() {
        return nextId;
    }

    private static Integer nextId = 1001;

    public void add (CourseDeliverable cd){
        cd.setId(nextId);
        cds.put(nextId++, cd);
    }

    public Collection<CourseDeliverable> getAll(){
        return cds.values();
    }
    public Integer useNextId(){
        return nextId++;
    }
    public CourseDeliverable get(Integer id){
        return cds.get(id);
    }

    public void delete(Integer id){
        cds.remove(id);
    }
}
