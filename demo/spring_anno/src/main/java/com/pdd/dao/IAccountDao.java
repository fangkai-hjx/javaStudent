package com.pdd.dao;

import com.pdd.domain.Account;

import java.util.List;

public interface IAccountDao {
    public abstract void saveAccount();

    public abstract List<Account> findAll();
    public abstract void update(Account money);

    public abstract Account findAccountByUid(Integer uid);
}
