package test;

import com.pdd.dao.IUserDao;
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
import java.util.Date;
import java.util.List;

public class UserDemo {
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
    public void findAll()  {
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println(users);
    }



    @Test
    public void findById() throws IOException {
        User user = userDao.findById(41);
        System.out.println(user);
    }

    @Test
    public void insert() throws IOException {
        User u = new User();
        u.setUsername("fangkai2");
        u.setBirthday(new Date());
        u.setAddress("深圳");
        u.setUserSex("女");
        System.out.println(userDao.saveUser(u));
        System.out.println(u);
    }

    @Test
    public void findTotal() throws IOException {
        System.out.println(userDao.findTotal());
    }

    @Test
    public void deleteUser() throws IOException {
        System.out.println(userDao.deleteUser(52));
    }

    @Test
    public void findUserByName() throws IOException {
        System.out.println(userDao.findUserByName("%fang%"));
    }

}
