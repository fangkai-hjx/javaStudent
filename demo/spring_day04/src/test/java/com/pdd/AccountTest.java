package com.pdd;

import com.pdd.domain.Account;
import com.pdd.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(locations = {"classpath:bean.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountTest {

    @Autowired
    private IAccountService accountService;

    @Test
    public void findAll(){
        List<Account> accounts = accountService.findAllAccounts();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void transfer(){
        accountService.transfer(42,41,500f);
    }
}
