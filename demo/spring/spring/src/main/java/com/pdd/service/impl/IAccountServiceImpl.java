package com.pdd.service.impl;

import com.pdd.dao.IAccountDao;
import com.pdd.dao.impl.IAccountDaoImpl;
import com.pdd.factory.BeanFactory;
import com.pdd.service.IAccountService;

public class IAccountServiceImpl implements IAccountService {
//    private IAccountDao accountDao = new IAccountDaoImpl();
    private IAccountDao accountDao = (IAccountDao) BeanFactory.getBean("accountDao");
    public IAccountServiceImpl(){
        System.out.println("初始化IAccountServiceImpl");
    }
    @Override
    public void saveAccount() {
//        accountDao = (IAccountDao) BeanFactory.getBean("accountDao");
        accountDao.saveAccount();
    }
}
