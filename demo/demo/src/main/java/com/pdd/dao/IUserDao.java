package com.pdd.dao;

import com.pdd.domain.User;

import java.util.List;

public interface IUserDao {
    /**
     * 查询所有用户，同时获取用户下所有账户信息
     * @return
     */
    List<User> findAll();

    List<User> findUserByName(String name);

    Integer findTotal();

    User findById(Integer id);
}
