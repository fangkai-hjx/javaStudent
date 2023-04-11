package com.pdd.service;

import com.pdd.domain.Account;

import java.util.List;

public interface IAccountService {
    public abstract void saveAccount();

    public abstract List<Account> findAll();
    public default void transfer(Integer sourceName,Integer targetName,Float money){};
    public abstract void updateAccount(int i );
    public abstract int deleteAccount();


}
