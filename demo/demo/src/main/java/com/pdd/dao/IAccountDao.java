package com.pdd.dao;

import com.pdd.domain.Account;
import com.pdd.domain.AccountUser;

import java.util.List;

public interface IAccountDao {
    /**
     * 查询所有账户，同时获取账户的所属用户名称以及他的地址信息
     * @return
     */
    List<Account> findAll();

    List<AccountUser> findAllAccount();

    Account findByUid();
}
