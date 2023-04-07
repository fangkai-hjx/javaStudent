package com.pdd.dao.impl;

import com.pdd.dao.IAccountDao;
import org.springframework.stereotype.Repository;

@Repository("accountDao1")
public class IAccountDaoImpl implements IAccountDao {
    public IAccountDaoImpl(){
        System.out.println("===IAccountDaoImpl1被创建了==");
    }
    @Override
    public void saveAccount() {
        System.out.println("保存账户信息1");
    }
}
