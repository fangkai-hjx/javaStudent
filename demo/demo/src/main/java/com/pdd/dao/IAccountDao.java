package com.pdd.dao;

import com.pdd.domain.Account;
import com.pdd.domain.AccountUser;

import java.util.List;

public interface IAccountDao {
    List<Account> findAll();

    List<AccountUser> findAllAccount();
}
