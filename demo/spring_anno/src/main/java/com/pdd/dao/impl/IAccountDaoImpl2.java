package com.pdd.dao.impl;

import com.pdd.dao.IAccountDao;
import com.pdd.domain.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("accountDao2")
public class IAccountDaoImpl2 implements IAccountDao {
    public IAccountDaoImpl2(){
        System.out.println("===IAccountDaoImpl2被创建了==");
    }
    @Override
    public void saveAccount() {
        System.out.println("保存账户信息2");
    }

    @Override
    public List<Account> findAll() {
        return null;
    }

    @Override
    public void update(Account money) {

    }

    @Override
    public Account findAccountByUid(Integer uid) {
        return null;
    }
}
