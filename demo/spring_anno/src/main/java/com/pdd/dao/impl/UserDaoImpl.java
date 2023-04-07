package com.pdd.dao.impl;

import com.pdd.dao.IUserDao;
import com.pdd.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements IUserDao {

    @Autowired
    private QueryRunner runner;

    @Override
    public List<User> selectAll() {
        try {
            return runner.query("select * from user", new BeanListHandler<User>(User.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User selectOneUser(Integer uid) {
        try {
            return runner.query("select * from user where id = ?", new BeanHandler<User>(User.class), uid);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        try {
            runner.update("update user set username = ?,address=?,birthday=?,sex=? where id = ?", user.getUsername(),
                    user.getAddress(), user.getBirthday(), user.getSex(), user.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertUser(User user) {
        try {
            runner.update("insert into user (id,username,birthday,sex,address) values (null,?,?,?,?)",
                    user.getUsername(), user.getBirthday(), user.getSex(), user.getAddress());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(Integer uid) {
        try {
            runner.update("delete from user where id = ?", uid);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
