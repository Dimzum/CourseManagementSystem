package com.team14.cms;


import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class RequestDao {
    private static Map<Integer, Request> requests = null;

    static {
        requests = new HashMap<Integer, Request>();
    }

    private static Integer nextId = 1001;

    public void add (Request req){
        req.setId(nextId);
        requests.put(nextId++, req);
    }

    public Integer getNextId(){
        return nextId;
    }

    public Integer useNextId(){
        return nextId++;
    }

    public Collection<Request> getAll(){
        return requests.values();
    }

    public Request get(Integer id){
        return requests.get(id);
    }

    public void delete(Integer id){
        requests.remove(id);
    }
}
