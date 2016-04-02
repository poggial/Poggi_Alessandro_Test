package com.faac.soa.dao;

import java.util.List;

import com.faac.soa.model.User;
public interface UserDAO {
    public List<User> list();
    public User get(int id);
    public User get(String username);
    public void saveOrUpdate(User user);
}