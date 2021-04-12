package com.team14.cms;

import org.apache.catalina.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AdminDao implements UserDao {
    private static Map<Integer, Administration> admins = null;

    static {
        admins = new HashMap<Integer, Administration>();

        admins.put(1001, new Administration(1001, "Admin", "Admin", "admin"));
    }

    private static Integer nextId = 1002;

    public void add (Administration admin){
        admin.setId(nextId);
        admins.put(nextId++, admin);
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

    @Override
    public Integer getNextId() {
        return null;
    }

    @Override
    public Integer useNextId() {
        return null;
    }
}
