package com.pdd.service;

import com.pdd.domain.Account;

import java.util.List;

public interface IAccountService {
    public abstract List<Account> findAllAccounts();
    public abstract void transfer(Integer sourceId,Integer targetId,Float money);
}
