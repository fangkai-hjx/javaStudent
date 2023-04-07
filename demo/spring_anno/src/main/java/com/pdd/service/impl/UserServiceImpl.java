package com.pdd.service.impl;

import com.pdd.domain.User;
import com.pdd.dao.IUserDao;
import com.pdd.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public List<User> findAllUser() {
        return userDao.selectAll();
    }

    @Override
    public User findUserById(Integer id) {
        return userDao.selectOneUser(id);
    }

    @Override
    public void saveUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public void deleteUser(Integer uid) {
        userDao.deleteUser(uid);
    }
}
