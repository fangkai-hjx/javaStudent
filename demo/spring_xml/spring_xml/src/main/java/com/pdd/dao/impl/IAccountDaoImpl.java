package com.pdd.dao.impl;

import com.pdd.dao.IAccountDao;

public class IAccountDaoImpl implements IAccountDao {
    public IAccountDaoImpl(){
        System.out.println("初始化IAccountDaoImpl");
    }
    @Override
    public void saveAccount() {
        System.out.println("保存账户信息到数据库");
    }
}
