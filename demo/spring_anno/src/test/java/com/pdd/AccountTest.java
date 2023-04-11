package com.pdd;

import com.pdd.config.SpringConfiguration;
import com.pdd.domain.Account;
import com.pdd.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")//@ContextConfiguration(classes = SpringConfiguration.class)
public class AccountTest {
    @Autowired
    @Qualifier("proxyAccountService")
    private IAccountService service;

    @Test
    public void testFindAll(){
        List<Account> accounts = service.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    /**
     * 45 500
     * 42 2500
     */
    @Test
    public void transfer(){
        service.transfer(42,45,500.f);
    }

}
