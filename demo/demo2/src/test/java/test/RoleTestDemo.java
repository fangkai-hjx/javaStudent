package test;

import com.pdd.dao.IRoleDao;
import com.pdd.domain.Role;
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

public class RoleTestDemo {
    private SqlSession session;
    private InputStream is;
    private IRoleDao roleDao;

    @Before
    public void init()throws IOException{
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        session = ssf.openSession();
        roleDao = session.getMapper(IRoleDao.class);
    }

    @After
    public void destory() throws IOException {
        session.commit();
        session.close();
        is.close();
    }

    @Test
    public void findALl() throws IOException {
        List<Role> roles = roleDao.findAll();
        for (Role role : roles) {
            System.out.println(role);
        }
    }

}
