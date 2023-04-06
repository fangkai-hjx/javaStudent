package com.pdd.test;

import com.pdd.dao.IUserDao;
import com.pdd.dao.impl.IUserDaoImpl;
import com.pdd.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class TestDemo {
    private SqlSession session;
    private InputStream is;
    private IUserDao userDao;

    @Before
    public void init()throws IOException{
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        session = ssf.openSession();
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destory() throws IOException {
        session.commit();
        session.close();
        is.close();
    }
    @Test
    public void test()  {
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }



    @Test
    public void selectByName() throws IOException {
        List<User> users = userDao.findUserByName("%王%");
        for (User user : users) {
            System.out.println();
        }
    }

    @Test
    public void findTotal() throws IOException {
        System.out.println(userDao.findTotal());
    }
}
