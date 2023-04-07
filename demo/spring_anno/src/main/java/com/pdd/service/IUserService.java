package com.pdd.service;

import com.pdd.domain.User;

import java.util.List;

public interface IUserService {
    List<User> findAllUser();

    User findUserById(Integer id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Integer uid);
}
