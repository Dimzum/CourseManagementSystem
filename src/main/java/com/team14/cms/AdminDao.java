package com.team14.cms;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AdminDao {
    private static Map<Integer, Administration> admins = null;

    static {
        admins = new HashMap<Integer, Administration>();

        admins.put(1001, new Administration(1001, "Admin", "Admin", "admin"));
    }

    private static Integer nextId = 1002;

    public void add (Administration prof){
        prof.setId(nextId);
        admins.put(nextId++, prof);
    }

    public Collection<Administration> getAll(){
        return admins.values();
    }

    public Administration get(Integer id){
        return admins.get(id);
    }

    public void delete(Integer id){
        admins.remove(id);
    }
}
