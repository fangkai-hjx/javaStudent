package com.pdd.dao;

import com.pdd.domain.Account;

import java.util.List;

public interface IAccountDao {
    public abstract List<Account> findAll();
    public abstract Account findAccount(Integer uid);

    public abstract void updateAccount(Integer uid, Float money);
}
