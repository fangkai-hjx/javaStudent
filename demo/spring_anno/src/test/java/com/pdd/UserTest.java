package com.pdd;

import com.pdd.config.SpringConfiguration;
import com.pdd.dao.IUserDao;
import com.pdd.domain.User;
import com.pdd.service.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

/**
 * User{id=41, username='老王', birthday=2018-02-27 17:47:08.0, sex='男', address='北京'}
 * User{id=42, username='小二王', birthday=2018-03-02 15:09:37.0, sex='女', address='北京金燕龙'}
 * User{id=43, username='小二王', birthday=2018-03-04 11:34:34.0, sex='女', address='北京金燕龙'}
 * User{id=45, username='传智播客', birthday=2018-03-04 12:04:06.0, sex='男', address='北京金燕龙'}
 * User{id=46, username='老王', birthday=2018-03-07 17:37:26.0, sex='男', address='北京'}
 * User{id=48, username='小马宝莉', birthday=2018-03-08 11:44:00.0, sex='女', address='北京修正'}
 * User{id=49, username='fangka', birthday=2023-04-06 12:54:01.0, sex='?', address='??'}
 * User{id=50, username='fangkai', birthday=2023-04-06 12:54:44.0, sex='?', address='??'}
 * User{id=51, username='fangkai2', birthday=2023-04-06 12:56:06.0, sex='?', address='??'}
 */
public class UserTest {
    IUserService service;

    @Before
    public void init(){
//        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        service = ac.getBean("userService", IUserService.class);
    }

    @Test
    public void testFindAllUser(){
        List<User> users = service.findAllUser();
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("...test...");
    }

    @Test
    public void testFindOneUser(){
        User user = service.findUserById(41);
        System.out.println(user);
    }

    @Test
    public void addUser(){
        User u = new User();
        u.setAddress("beijing");
        u.setUsername("test");
        u.setSex("女");
        u.setBirthday(new Date());
        service.saveUser(u);
    }

    @Test
    public void updateUser(){

    }

    @Test
    public void deleteUser(){
        service.deleteUser(53);
    }

}
