package com.pdd.dao.impl;

import com.pdd.dao.IUserDao;
import com.pdd.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class IUserDaoImpl implements IUserDao {
    private SqlSessionFactory factory;
    public IUserDaoImpl(SqlSessionFactory factory){
        this.factory = factory;
    }

    public List<User> findAll() {
        SqlSession sqlSession = factory.openSession();
        List<User> list = sqlSession.selectList("com.pdd.dao.IUserDao.findAll");
        sqlSession.close();
        return list;
    }

    public List<User> findUserByName(String name) {
        return null;
    }

    public Integer findTotal() {
        return null;
    }

    public User findById(Integer id) {
        return null;
    }
}
