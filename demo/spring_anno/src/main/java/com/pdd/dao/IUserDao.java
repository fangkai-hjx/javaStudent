package com.pdd.dao;

import com.pdd.domain.User;

import java.util.List;

public interface IUserDao {

    public abstract List<User> selectAll();

    public abstract User selectOneUser(Integer uid);

    public abstract void update(User user);

    public abstract void insertUser(User user);

    public abstract void deleteUser(Integer uid);
}
