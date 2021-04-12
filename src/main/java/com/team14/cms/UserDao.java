package com.team14.cms;

import java.util.Collection;

public interface UserDao {
    public Integer getNextId();
    public Integer useNextId();
    public void delete(Integer id);
}
