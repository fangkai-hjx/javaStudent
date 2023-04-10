package com.pdd.service.impl;

import com.pdd.dao.IAccountDao;
import com.pdd.domain.Account;
import com.pdd.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    public List<Account> findAllAccounts() {
        System.out.println("执行查询操作");
        return accountDao.findAll();
    }

    /**
     * @param sourceId
     * @param targetId
     * @param money
     */
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void transfer(Integer sourceId, Integer targetId, Float money) {
        Account a1 = accountDao.findAccount(sourceId);
        accountDao.updateAccount(sourceId, a1.getMoney() - money);
        Account a2 = accountDao.findAccount(targetId);
        int i = 1/0;
        accountDao.updateAccount(targetId, a2.getMoney() + money);
    }
}
