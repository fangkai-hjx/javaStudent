package com.pdd.ui;

import com.pdd.dao.IAccountDao;
import com.pdd.service.IAccountService;
import com.pdd.service.impl.IAccountServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * ApplicationContext的三个常见是嫌累
 *          ClassPathXmlApplicationContext：加载类路径下的配置文件
 *          FileSystemXmlApplicationContext：加载此片任意路径下的配置文件
 *          AnnotationConfigApplicationContext：用于读取注解创建容器
 *
 * 核心容器的两个接口引发的问题：
 *       ApplicationContext的三个常见是嫌累：在创建核心容器时，创建对象是采用立刻加载的方式
 *              - 适用单例模式      ----更多使用
 *       BeanFactory:延迟加载
 *              - 适用多例模式
 */
public class Client {
    public static void main(String[] args) {
        // 1 获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 2 根据id获取bean对象

        IAccountService accountService1 = (IAccountService)ac.getBean("accountService");
        IAccountService accountService2 = (IAccountService)ac.getBean("accountService");
//        IAccountDao accountDao = ac.getBean("accountDao",IAccountDao.class);
        System.out.println(accountService1);
        accountService1.saveAccount();
        ClassPathXmlApplicationContext a = (ClassPathXmlApplicationContext)ac;
        a.close();
//        System.out.println(accountDao);

        //lazy loading
//        Resource resource = new ClassPathResource("bean.xml");
//        BeanFactory factory = new XmlBeanFactory(resource);
    }
}
