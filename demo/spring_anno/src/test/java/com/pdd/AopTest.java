package com.pdd;

import com.pdd.config.SpringConfiguration;
import com.pdd.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        IAccountService as = ac.getBean("accountService",IAccountService.class);
        as.saveAccount();
//        as.deleteAccount();
//        as.updateAccount(1);
    }
}
