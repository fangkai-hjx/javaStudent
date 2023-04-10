package com.pdd.service.impl;

import com.pdd.dao.IAccountDao;
import com.pdd.domain.Account;
import com.pdd.service.IAccountService;

import java.util.List;

public class AccountServiceImpl implements IAccountService {
    private IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> findAllAccounts() {
        return accountDao.findAll();
    }
}
