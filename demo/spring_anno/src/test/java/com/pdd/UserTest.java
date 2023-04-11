package com.pdd;

import com.pdd.config.SpringConfiguration;
import com.pdd.dao.IUserDao;
import com.pdd.domain.User;
import com.pdd.service.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * spring整合junit的配置
 *      1.导入坐标
 *      2.使用junit提供的注解把原有的main方法替换为spring提供的
 *      3 告知spring的运行器，spring和ioc的创建是基于xml还是注解的，并且说明位置
 *          @ContextConfiguration：location：指定xml的位置，加上classpath关键字  classed：指定注解类所在的位置
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class UserTest {
    @Autowired
    IUserService service;

//    @Before
//    public void init(){
////        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
//        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
//        service = ac.getBean("userService", IUserService.class);
//    }

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
