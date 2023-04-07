package com.pdd.ui;

import com.pdd.dao.impl.IAccountDaoImpl;
import com.pdd.service.impl.IAccountServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountServiceImpl accountService = ac.getBean("accountService", IAccountServiceImpl.class);
        IAccountServiceImpl accountService2 = ac.getBean("accountService", IAccountServiceImpl.class);
        accountService.saveAccount();
        System.out.println(accountService == accountService2);
//        IAccountDaoImpl accountDao = ac.getBean("accountDao1", IAccountDaoImpl.class);
//        accountService.saveAccount();
    }
}
