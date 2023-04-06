package com.pdd.service.impl;

import com.pdd.dao.IAccountDao;
import com.pdd.dao.impl.IAccountDaoImpl;
import com.pdd.service.IAccountService;

public class IAccountServiceImpl implements IAccountService {
    private IAccountDao accountDao = new IAccountDaoImpl();
    public IAccountServiceImpl(){
        System.out.println("被初始化IAccountServiceImpl");
    }
    @Override
    public void saveAccount() {
        accountDao.saveAccount();
    }
    public void init(){
        System.out.println("init");
    }

    public void destory(){
        System.out.println("destory");
    }
}
