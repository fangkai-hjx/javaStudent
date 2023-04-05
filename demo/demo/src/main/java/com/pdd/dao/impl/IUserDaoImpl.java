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

    @Override
    public List<User> findAll() {
        SqlSession sqlSession = factory.openSession();
        List<User> list = sqlSession.selectList("com.pdd.dao.IUserDao.findAll");
        sqlSession.close();
        return list;
    }

    @Override
    public List<User> findUserByName(String name) {
        return null;
    }

    @Override
    public Integer findTotal() {
        return null;
    }
}
