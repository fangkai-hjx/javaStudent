package com.pdd.dao.impl;

import com.pdd.dao.IAccountDao;
import org.springframework.stereotype.Repository;

@Repository("accountDao2")
public class IAccountDaoImpl2 implements IAccountDao {
    public IAccountDaoImpl2(){
        System.out.println("===IAccountDaoImpl2被创建了==");
    }
    @Override
    public void saveAccount() {
        System.out.println("保存账户信息2");
    }
}
