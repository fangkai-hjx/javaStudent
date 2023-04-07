package com.pdd.service.impl;

import com.pdd.dao.IAccountDao;
import com.pdd.dao.impl.IAccountDaoImpl;
import com.pdd.service.IAccountService;

public class AccountServiceImpl implements IAccountService {
    private IAccountDao accountDao = new IAccountDaoImpl();
    @Override
    public void saveAccount() {
        accountDao.findAll();
    }
}
