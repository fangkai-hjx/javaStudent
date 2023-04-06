package com.pdd.test;

import com.pdd.dao.IAccountDao;
import com.pdd.dao.IUserDao;
import com.pdd.dao.impl.IUserDaoImpl;
import com.pdd.domain.Account;
import com.pdd.domain.AccountUser;
import com.pdd.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AccountTestDemo {
    private SqlSession session;
    private InputStream is;
    private IAccountDao accountDao;

    @Before
    public void init()throws IOException{
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        session = ssf.openSession();
        accountDao = session.getMapper(IAccountDao.class);
    }

    @After
    public void destory() throws IOException {
        session.commit();
        session.close();
        is.close();
    }

    @Test
    public void findALl() throws IOException {
        List<Account> accounts = accountDao.findAll();
//        for (Account account : accounts) {
//            System.out.println(account.getUser());
//            System.out.println(account);
//        }
    }

    @Test
    public void findAllAccount() throws IOException {
        List<AccountUser> account = accountDao.findAllAccount();
        for (AccountUser accountUser : account) {
            System.out.println(accountUser);
        }
    }
}
